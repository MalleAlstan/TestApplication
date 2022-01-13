package com.example.testapplication.di

import com.example.testapplication.source.local.room.RoomDB
import com.example.testapplication.source.local.room.RoomDBImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface DbModule {

    @Binds
    fun provideMyRoomImpl(myRoom: RoomDBImpl): RoomDB
}