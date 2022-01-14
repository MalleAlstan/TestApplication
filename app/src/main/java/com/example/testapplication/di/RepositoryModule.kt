package com.example.testapplication.di

import com.example.testapplication.repo.currency.CurrencyRepository
import com.example.testapplication.repo.currency.CurrencyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideCurrencyRepositoryImpl(dataRepository: CurrencyRepositoryImpl): CurrencyRepository
}