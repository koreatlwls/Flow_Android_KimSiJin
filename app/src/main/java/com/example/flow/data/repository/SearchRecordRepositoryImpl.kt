package com.example.flow.data.repository

import com.example.flow.data.local.source.SearchRecordLocalDataSource
import com.example.flow.model.SearchRecord
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRecordRepositoryImpl @Inject constructor(
    private val searchRecordLocalDataSource: SearchRecordLocalDataSource
) : SearchRecordRepository {

    override suspend fun insertSearchRecord(searchRecord: SearchRecord) {
        searchRecordLocalDataSource.insertSearchRecord(searchRecord)
    }

    override fun getAllSearchRecords(): Flow<List<SearchRecord>> {
        return searchRecordLocalDataSource.getAllSearchRecords()
    }

    override suspend fun deleteAllSearchRecords() {
        searchRecordLocalDataSource.deleteAllSearchRecords()
    }

}