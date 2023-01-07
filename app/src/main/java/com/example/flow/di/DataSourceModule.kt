package com.example.flow.di

import com.example.flow.data.local.source.SearchRecordLocalDataSource
import com.example.flow.data.local.source.SearchRecordLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Singleton
    fun provideSearchRecordLocalDataSource(
        searchRecordLocalDataSourceImpl: SearchRecordLocalDataSourceImpl
    ): SearchRecordLocalDataSource

}