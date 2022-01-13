package com.example.testapplication.di

import com.example.testapplication.source.Source
import com.example.testapplication.source.local.LocalSourceImpl
import com.example.testapplication.source.remote.RemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier


@Module
@InstallIn(SingletonComponent::class)
interface SourceModule {

    @Binds
    @RemoteData
    fun provideDataSourceRemoteImpl(remoteDataSource: RemoteSourceImpl): Source

    @Binds
    @LocalData
    fun provideDataSourceLocalImpl(remoteDataSource: LocalSourceImpl): Source

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RemoteData

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalData
}


