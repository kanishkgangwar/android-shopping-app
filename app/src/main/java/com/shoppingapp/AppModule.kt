package com.shoppingapp

import android.content.Context
import androidx.room.Room
import com.shoppingapp.data.local.dao.CartItemDao
import com.shoppingapp.data.local.dao.CreditCardDao
import com.shoppingapp.data.local.dao.DeliveryAddressDao
import com.shoppingapp.data.local.dao.RecentSearchDao
import com.shoppingapp.data.local.dao.UserDao
import com.shoppingapp.data.local.datastore.AppDatabase
import com.shoppingapp.data.remote.RemoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRemoteService(): RemoteService {
        return RemoteService()
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "shopApp_db"
        )
        .fallbackToDestructiveMigration(true)
        .build()
    }

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    fun provideCartDao(db: AppDatabase): CartItemDao = db.cartItemDao()

    @Provides
    fun provideSearchDao(db: AppDatabase): RecentSearchDao = db.queryDao()

    @Provides
    fun provideAddressDao(db: AppDatabase): DeliveryAddressDao = db.deliveryDao()

    @Provides
    fun provideCreditCardDao(db: AppDatabase): CreditCardDao = db.creditCardDao()
}