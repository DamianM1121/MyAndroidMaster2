package com.aristidevs.androidmaster.superheroapp.Database.SuperHeroDB.Module

import android.content.Context
import androidx.room.Room
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroDao.SearchHistoryDao
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SuperRegisterModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SuperHeroDatabase {
        return Room.databaseBuilder(
            context,
            SuperHeroDatabase::class.java,
            "superheroes_db"
        ).build()
    }

    @Provides
    fun provideHeroSearchHistoryDao(db: SuperHeroDatabase): SearchHistoryDao {
        return db.searchHistoryDao()
    }
}