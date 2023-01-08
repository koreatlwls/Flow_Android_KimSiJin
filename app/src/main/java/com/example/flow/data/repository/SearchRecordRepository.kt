package com.example.flow.data.repository

import com.example.flow.model.SearchRecord
import kotlinx.coroutines.flow.Flow

interface SearchRecordRepository {

    suspend fun insertSearchRecord(searchRecord: SearchRecord)

    fun getAllSearchRecords(): Flow<List<SearchRecord>>

    suspend fun deleteAllSearchRecords()

}