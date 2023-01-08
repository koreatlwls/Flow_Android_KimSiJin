package com.example.flow.data.local.source

import com.example.flow.data.local.database.dao.SearchRecordDao
import com.example.flow.model.SearchRecord
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRecordLocalDataSourceImpl @Inject constructor(
    private val searchRecordDao: SearchRecordDao
) : SearchRecordLocalDataSource {

    override suspend fun insertSearchRecord(searchRecord: SearchRecord) {
        searchRecordDao.insertSearchRecord(searchRecord.toEntity())
    }

    override fun getAllSearchRecords(): Flow<List<SearchRecord>> {
        return searchRecordDao.getAllSearchRecords().map { searchRecordEntities ->
            searchRecordEntities.map { searchRecordEntity ->
                searchRecordEntity.toSearchRecord()
            }
        }
    }

    override suspend fun deleteAllSearchRecords() {
        searchRecordDao.deleteAllSearchRecords()
    }

}