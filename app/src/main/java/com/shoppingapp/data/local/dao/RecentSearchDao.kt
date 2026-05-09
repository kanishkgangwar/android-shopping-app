package com.shoppingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shoppingapp.data.local.entity.RecentSearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentSearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuery(query: RecentSearchEntity)

    @Query("SELECT * FROM recent_search_table ORDER BY id DESC")
    fun getAllQuery(): Flow<List<RecentSearchEntity>>

    @Query("DELETE FROM recent_search_table WHERE id= :id")
    suspend fun deleteQueryById(id: Long)

    @Query("DELETE FROM recent_search_table")
    suspend fun deleteAllQueries()
}
