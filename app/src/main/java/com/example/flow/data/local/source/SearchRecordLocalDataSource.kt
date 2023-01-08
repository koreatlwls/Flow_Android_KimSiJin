package com.example.flow.data.local.source

import com.example.flow.model.SearchRecord
import kotlinx.coroutines.flow.Flow

interface SearchRecordLocalDataSource {

    suspend fun insertSearchRecord(searchRecord: SearchRecord)

    fun getAllSearchRecords(): Flow<List<SearchRecord>>

    suspend fun deleteAllSearchRecords()

}