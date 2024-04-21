package com.example.assessment_pvdt_bangalore.di

import com.example.assessment_pvdt_bangalore.api_call.PavdtApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Singleton
    @Provides
    fun pvdtApi(): PavdtApi {
        return PavdtApi.create()
    }
}