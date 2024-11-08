package com.aristidevs.androidmaster.superheroapp.Database.SuperHeroDB.Module

import com.aristidevs.androidmaster.superheroapp.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Provee la instancia de Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/") // Base URL de la API
            .addConverterFactory(GsonConverterFactory.create()) // Conversor JSON
            .build()
    }

    // Provee la instancia de ApiService
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
