package com.shoppingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shoppingapp.data.local.entity.DeliveryAddressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DeliveryAddressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewAddress(newAddress: DeliveryAddressEntity)

    @Query("UPDATE delivery_address_table SET placeTitle= :placeTitle, placeAddress= :placeAddress WHERE id= :id")
    suspend fun updateAddress(id: Long, placeTitle: String, placeAddress: String)

    @Query("SELECT * FROM delivery_address_table ORDER BY id DESC")
    fun getAllAddress(): Flow<List<DeliveryAddressEntity>>

    @Query("DELETE FROM delivery_address_table WHERE id= :id")
    suspend fun deleteAddressById(id: Long)
}