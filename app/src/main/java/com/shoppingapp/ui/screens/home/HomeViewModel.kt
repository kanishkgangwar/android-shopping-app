package com.shoppingapp.ui.screens.home

import androidx.lifecycle.ViewModel
import com.shoppingapp.data.Repository
import com.shoppingapp.ui.screens.CategoryItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class Product(
    val image: String,
    val name: String,
    val price: String
)

data class Category(
    val icon: Int,
    val label: String,
    val type: CategoryItemState
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _selectedCategory = MutableStateFlow(CategoryItemState.MALE)
    val selectedCategory: StateFlow<CategoryItemState> = _selectedCategory

    fun selectCategory(category: CategoryItemState) {
        _selectedCategory.value = category
    }
}