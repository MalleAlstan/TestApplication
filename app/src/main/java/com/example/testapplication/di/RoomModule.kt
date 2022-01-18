package com.example.testapplication.di

import android.content.Context
import androidx.room.Room
import com.example.testapplication.model.data.CurrencyInfo
import com.example.testapplication.source.local.room.CryptoCurrencyDatabase
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.lang.reflect.Type
import javax.inject.Qualifier


@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    private const val CRYPTO_CURRENCY = "crypto_currency"
    private val currencyInfoType: Type = Types.newParameterizedType(List::class.java, CurrencyInfo::class.java)

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

    @Provides
    fun provideCurrencyInfoListJsonAdapter(moshi: Moshi): JsonAdapter<List<CurrencyInfo>> = moshi.adapter(currencyInfoType)

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CurrencyTable
}