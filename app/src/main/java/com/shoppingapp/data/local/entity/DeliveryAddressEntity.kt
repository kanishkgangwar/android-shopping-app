package com.shoppingapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "delivery_address_table")
data class DeliveryAddressEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val placeTitle: String,
    val placeAddress: String
)