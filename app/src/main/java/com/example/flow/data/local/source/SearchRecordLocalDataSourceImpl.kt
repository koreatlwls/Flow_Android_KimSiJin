package com.example.flow.data.local.source

import com.example.flow.data.local.database.dao.SearchRecordDao
import com.example.flow.model.SearchRecord
import javax.inject.Inject

class SearchRecordLocalDataSourceImpl @Inject constructor(
    private val searchRecordDao: SearchRecordDao
) : SearchRecordLocalDataSource {

    override suspend fun insertSearchRecord(searchRecord: SearchRecord) {
        searchRecordDao.insertSearchRecord(searchRecord.toEntity())
    }

}