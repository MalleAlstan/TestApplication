package com.example.testapplication.di

import android.content.Context
import androidx.room.Room
import com.example.testapplication.source.local.room.RoomDatabase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    private const val CURRENCY_INFO = "currency_info"

    @Singleton
    @Provides
    @CurrencyTable
    fun provideCurrencyTableDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        RoomDatabase::class.java,
        CURRENCY_INFO
    ).build()

    @Singleton
    @Provides
    fun provideCurrencyInfoDao(@CurrencyTable database: RoomDatabase) = database.currencyInfoDao()

    @Singleton
    @Provides
    fun provideMoshi() = Moshi.Builder().build()

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CurrencyTable
}