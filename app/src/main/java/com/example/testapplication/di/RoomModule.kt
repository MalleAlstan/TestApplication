package com.example.testapplication.di

import android.content.Context
import androidx.room.Room
import com.example.testapplication.source.local.room.CryptoCurrencyDatabase
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

    private const val CRYPTO_CURRENCY = "crypto_currency"

    @Provides
    @CurrencyTable
    fun provideCryptoCurrencyDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        CryptoCurrencyDatabase::class.java,
        CRYPTO_CURRENCY
    ).build()

    @Provides
    fun provideCurrencyInfoDao(@CurrencyTable database: CryptoCurrencyDatabase) = database.currencyInfoDao()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CurrencyTable
}