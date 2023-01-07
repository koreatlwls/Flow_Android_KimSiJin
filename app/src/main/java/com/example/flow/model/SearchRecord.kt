package com.example.flow.model

import com.example.flow.data.local.model.SearchRecordEntity

data class SearchRecord(
    val query: String
) {

    fun toEntity() = SearchRecordEntity(
        query = query
    )

}
