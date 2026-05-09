package com.shoppingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shoppingapp.data.local.entity.CreditCardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CreditCardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCreditCard(creditCardEntity: CreditCardEntity)

    @Query("SELECT * FROM credit_card_table ORDER BY id DESC")
    fun getAllCreditCard(): Flow<List<CreditCardEntity>>

    @Query("DELETE FROM credit_card_table WHERE id= :id")
    suspend fun deleteCreditCard(id: Long)
}