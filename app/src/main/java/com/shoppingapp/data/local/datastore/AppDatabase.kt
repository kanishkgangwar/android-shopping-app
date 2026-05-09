package com.shoppingapp.data.local.datastore

import androidx.room.Database
import androidx.room.RoomDatabase
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

@Database(
    entities = [
        UserEntity::class,
        RecentSearchEntity::class,
        CartItemEntity::class,
        DeliveryAddressEntity::class,
        CreditCardEntity::class
               ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun queryDao(): RecentSearchDao
    abstract fun cartItemDao(): CartItemDao
    abstract fun deliveryDao(): DeliveryAddressDao
    abstract fun creditCardDao(): CreditCardDao
}