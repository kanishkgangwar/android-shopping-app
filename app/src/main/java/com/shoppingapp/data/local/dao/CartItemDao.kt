package com.shoppingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shoppingapp.data.local.entity.CartItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCartItem(cartItem: CartItemEntity)

    @Query("SELECT * FROM cart_item_table ORDER BY productId DESC")
    fun getAllCartItem(): Flow<List<CartItemEntity>>

    @Query("UPDATE cart_item_table SET quantity = quantity +1 WHERE productId= :productId")
    suspend fun increaseQuantity(productId: Int)

    @Query("UPDATE cart_item_table SET quantity = quantity -1 WHERE productId= :productId AND quantity > 1")
    suspend fun decreaseQuantity(productId: Int)

    @Query("DELETE FROM cart_item_table WHERE productId= :productId")
    suspend fun deleteCartItem(productId: Int)
}