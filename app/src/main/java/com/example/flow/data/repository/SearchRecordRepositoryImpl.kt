package com.example.flow.data.repository

import com.example.flow.data.local.source.SearchRecordLocalDataSource
import com.example.flow.model.SearchRecord
import javax.inject.Inject

class SearchRecordRepositoryImpl @Inject constructor(
    private val searchRecordLocalDataSource: SearchRecordLocalDataSource
) : SearchRecordRepository {

    override suspend fun insertSearchRecord(searchRecord: SearchRecord) {
        searchRecordLocalDataSource.insertSearchRecord(searchRecord)
    }

}