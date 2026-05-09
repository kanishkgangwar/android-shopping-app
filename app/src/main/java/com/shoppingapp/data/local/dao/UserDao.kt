package com.shoppingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.shoppingapp.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun registerUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("SELECT * FROM user_table WHERE email= :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Query("SELECT * FROM user_table WHERE LOWER(email)= LOWER(:email) AND password= :password LIMIT 1")
    suspend fun loginUser(
        email: String,
        password: String
    ): UserEntity?
}