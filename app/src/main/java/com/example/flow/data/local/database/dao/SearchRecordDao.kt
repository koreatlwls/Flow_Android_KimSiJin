package com.example.flow.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.flow.data.local.model.SearchRecordEntity

@Dao
interface SearchRecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchRecord(searchRecordEntity: SearchRecordEntity)

}