package com.example.testapplication.di

import android.content.Context
import androidx.room.Room
import com.example.testapplication.source.local.room.RoomDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier


@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    private const val CURRENCY_INFO = "currency_info"

    @Provides
    @CurrencyTable
    fun provideCurrencyTableDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        RoomDatabase::class.java,
        CURRENCY_INFO
    ).build()

    @Provides
    fun provideCurrencyInfoDao(@CurrencyTable database: RoomDatabase) = database.currencyInfoDao()

    @Provides
    fun provideMoshi() = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CurrencyTable
}