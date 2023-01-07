package com.example.flow.di

import com.example.flow.data.repository.MovieRepository
import com.example.flow.data.repository.MovieRepositoryImpl
import com.example.flow.data.repository.SearchRecordRepository
import com.example.flow.data.repository.SearchRecordRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    @Singleton
    fun provideSearchRecordRepository(
        searchRecordRepositoryImpl: SearchRecordRepositoryImpl
    ): SearchRecordRepository

}