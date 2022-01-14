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
abstract class SourceModule {

    @Binds
    @RemoteData
    abstract fun provideDataSourceRemoteImpl(remoteSource: RemoteSourceImpl): Source

    @Binds
    @LocalData
    abstract fun provideDataSourceLocalImpl(remoteSource: LocalSourceImpl): Source

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RemoteData

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalData
}


