package com.shoppingapp.ui.screens

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoppingapp.data.Repository
import com.shoppingapp.data.local.entity.CartItemEntity
import com.shoppingapp.data.local.entity.DeliveryAddressEntity
import com.shoppingapp.data.local.entity.UserEntity
import com.shoppingapp.ui.components.ProductUiModel
import com.shoppingapp.ui.components.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ScreenState { BACK, MENU }
enum class CategoryItemState { MALE, FEMALE, ACCESSORIES, GROCERIES }
enum class CheckOutState { ADDRESS, PAYMENT, COMPLETE }
enum class PaymentType { CASH, CARD }

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    init {
        fetchProductData()
    }

    val gender = listOf("Male", "Female", "Other")

    private val _allProducts = MutableStateFlow<List<ProductUiModel>>(emptyList())
    val allProducts: StateFlow<List<ProductUiModel>> = _allProducts

    private val _filteredProducts = MutableStateFlow<List<ProductUiModel>>(emptyList())
    val filteredProducts: StateFlow<List<ProductUiModel>> = _filteredProducts

    private val _currentUser = MutableStateFlow<UserEntity?>(null)
    val currentUser = _currentUser.asStateFlow()

    private val _error = MutableLiveData<String>()
    val error: MutableLiveData<String> = _error

    private fun fetchProductData() {
        viewModelScope.launch {
            try {
                val data = repository.getProductData()
                val uiList = data.map { it.toUiModel() }

                _allProducts.value = uiList
                Log.d("ALL_PRODUCTS", "fetchProductData: ${allProducts.value}")
                Log.d("PRODUCT_REQUEST", "products data fetch: SUCCESS")
            }
            catch(e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun filterBySearch(query: String) {
        val titleMatches = _allProducts.value.filter {
            it.title.contains(query, ignoreCase = true)
        }

        _filteredProducts.value = if (titleMatches.isNotEmpty()) {
            titleMatches
        } else {
            _allProducts.value.filter {
                it.category.contains(query, ignoreCase = true)
            }
        }
    }

    // search query
    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    // Cart item
    fun addCartItem(cartItem: CartItemEntity) {
        viewModelScope.launch {
            repository.addCartItem(cartItem)
        }
    }

    val allCartItem = repository.getAllCartItem()

    fun increaseQuantity(productId: Int) {
        viewModelScope.launch {
            repository.increaseQuantity(productId)
        }
    }

    fun decreaseQuantity(productId: Int) {
        viewModelScope.launch {
            repository.decreaseQuantity(productId)
        }
    }

    fun deleteCartItem(productId: Int) {
        viewModelScope.launch {
            repository.deleteCartItem(productId)
        }
    }

    val subtotal: StateFlow<Int> = combine(
        allCartItem,
        allProducts
    ) { cartItems, products ->

        val productMap = products.associateBy { it.id }

        cartItems.sumOf { item ->
            val product = productMap[item.productId]
            (product?.price ?: 0) * item.quantity
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        0
    )

    fun setCurrentUser(user: UserEntity) {
        _currentUser.value = user
    }
}