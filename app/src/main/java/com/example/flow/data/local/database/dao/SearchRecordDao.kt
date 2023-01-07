package com.example.flow.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flow.data.local.model.SearchRecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchRecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchRecord(searchRecordEntity: SearchRecordEntity)

    @Query("SELECT * FROM SearchRecordEntity ORDER BY id DESC LIMIT 10")
    fun getAllSearchRecords(): Flow<List<SearchRecordEntity>>

}