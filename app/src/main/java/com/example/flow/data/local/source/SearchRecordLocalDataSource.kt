package com.example.flow.data.local.source

import com.example.flow.model.SearchRecord

interface SearchRecordLocalDataSource {

    suspend fun insertSearchRecord(searchRecord: SearchRecord)

}