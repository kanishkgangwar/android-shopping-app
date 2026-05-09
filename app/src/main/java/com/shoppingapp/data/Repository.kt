package com.shoppingapp.data

import androidx.room.Dao
import com.shoppingapp.data.local.dao.CartItemDao
import com.shoppingapp.data.local.dao.CreditCardDao
import com.shoppingapp.data.local.dao.DeliveryAddressDao
import com.shoppingapp.data.local.dao.RecentSearchDao
import com.shoppingapp.data.local.dao.UserDao
import com.shoppingapp.data.local.entity.CartItemEntity
import com.shoppingapp.data.local.entity.CreditCardEntity
import com.shoppingapp.data.local.entity.DeliveryAddressEntity
import com.shoppingapp.data.local.entity.RecentSearchEntity
import com.shoppingapp.data.local.entity.UserEntity
import com.shoppingapp.data.remote.Product
import com.shoppingapp.data.remote.RemoteService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteService: RemoteService,
    private val userDao: UserDao,
    private val cartDao: CartItemDao,
    private val searchDao: RecentSearchDao,
    private val deliveryDao: DeliveryAddressDao,
    private val creditCardDao: CreditCardDao
) {
    // User functions
    suspend fun registerUser(user: UserEntity) {
        userDao.registerUser(user)
    }
    suspend fun getUserByEmail(email: String): UserEntity? {
        return userDao.getUserByEmail(email)
    }
    suspend fun loginUser(email: String, password: String): UserEntity? {
        return userDao.loginUser(email, password)
    }
    suspend fun updateUser(user: UserEntity) {
        userDao.updateUser(user)
    }

    // Products
    suspend fun getProductData(): List<Product> {
        val response = remoteService.api.getProductData()

        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        }
        else {
            throw Exception("Error: ${response.code()}")
        }
    }

    // Search query
    suspend fun addQuery(query: RecentSearchEntity) {
        searchDao.addQuery(query)
    }

    fun getAllQuery(): Flow<List<RecentSearchEntity>> {
        return searchDao.getAllQuery()
    }

    suspend fun deleteQueryById(id: Long) {
        searchDao.deleteQueryById(id)
    }

    suspend fun deleteAllQueries() {
        searchDao.deleteAllQueries()
    }

    // Cart item
    suspend fun addCartItem(cartItem: CartItemEntity) {
        cartDao.addCartItem(cartItem)
    }

    fun getAllCartItem(): Flow<List<CartItemEntity>> {
        return cartDao.getAllCartItem()
    }

    suspend fun increaseQuantity(productId: Int) {
        cartDao.increaseQuantity(productId)
    }

    suspend fun decreaseQuantity(productId: Int) {
        cartDao.decreaseQuantity(productId)
    }

    suspend fun deleteCartItem(productId: Int) {
        cartDao.deleteCartItem(productId)
    }

    suspend fun addNewAddress(newAddress: DeliveryAddressEntity) {
        deliveryDao.addNewAddress(newAddress)
    }

    suspend fun updateAddress(id: Long, placeTitle: String, placeAddress: String) {
        deliveryDao.updateAddress(id, placeTitle, placeAddress)
    }

    fun getAllAddress(): Flow<List<DeliveryAddressEntity>> {
        return deliveryDao.getAllAddress()
    }

    suspend fun deleteAddressById(id: Long) {
        deliveryDao.deleteAddressById(id)
    }

    // Credit Card
    suspend fun addCreditCard(creditCardEntity: CreditCardEntity) {
        creditCardDao.addCreditCard(creditCardEntity)
    }

    fun getAllCreditCard(): Flow<List<CreditCardEntity>> {
        return creditCardDao.getAllCreditCard()
    }

    suspend fun deleteCreditCard(id: Long) {
        creditCardDao.deleteCreditCard(id)
    }
}