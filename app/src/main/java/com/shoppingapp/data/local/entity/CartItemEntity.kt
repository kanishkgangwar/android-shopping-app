package com.shoppingapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item_table")
data class CartItemEntity (
    @PrimaryKey val productId: Int,
    val quantity: Int
)