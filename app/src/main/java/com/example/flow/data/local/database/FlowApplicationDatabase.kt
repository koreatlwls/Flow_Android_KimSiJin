package com.example.flow.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flow.data.local.database.dao.SearchRecordDao
import com.example.flow.data.local.model.SearchRecordEntity

@Database(
    entities = [SearchRecordEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FlowApplicationDatabase : RoomDatabase() {

    abstract fun getSearchRecordDao(): SearchRecordDao

    companion object {
        const val DB_NAME = "Flow.db"
    }

}