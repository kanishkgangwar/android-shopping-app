package com.shoppingapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "credit_card_table")
data class CreditCardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val cardHolderName: String,
    val cardNumber: String,
    val expiryDate: String,
    val cvv: String
)