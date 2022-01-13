package com.example.testapplication.di

import com.example.testapplication.model.room.RoomDB
import com.example.testapplication.model.room.RoomDBImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface CacheModule {

    @Binds
    fun provideMyRoomImpl(myRoom: RoomDBImpl): RoomDB
}