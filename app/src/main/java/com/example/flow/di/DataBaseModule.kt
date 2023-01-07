package com.example.flow.di

import android.content.Context
import androidx.room.Room
import com.example.flow.data.local.database.FlowApplicationDatabase
import com.example.flow.data.local.database.FlowApplicationDatabase.Companion.DB_NAME
import com.example.flow.data.local.database.dao.SearchRecordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideApplicationDatabase(@ApplicationContext context: Context): FlowApplicationDatabase {
        return Room.databaseBuilder(
            context,
            FlowApplicationDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideSearchRecordDao(
        flowApplicationDatabase: FlowApplicationDatabase
    ): SearchRecordDao {
        return flowApplicationDatabase.getSearchRecordDao()
    }

}