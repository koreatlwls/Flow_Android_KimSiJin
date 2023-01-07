package com.example.flow.data.repository

import com.example.flow.model.SearchRecord

interface SearchRecordRepository {

    suspend fun insertSearchRecord(searchRecord: SearchRecord)

}