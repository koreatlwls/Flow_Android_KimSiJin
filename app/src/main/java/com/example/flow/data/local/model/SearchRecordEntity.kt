package com.example.flow.data.local.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.flow.model.SearchRecord

@Entity(indices = [Index(value = ["query"], unique = true)])
data class SearchRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val query: String
) {

    fun toSearchRecord() = SearchRecord(
        query = query
    )

}