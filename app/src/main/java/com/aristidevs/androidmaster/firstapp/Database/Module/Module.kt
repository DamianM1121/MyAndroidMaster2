package com.aristidevs.androidmaster.firstapp.Database.Module

import android.content.Context
import androidx.room.Room
import com.aristidevs.androidmaster.Application.MyApplication
import com.aristidevs.androidmaster.firstapp.Database.NameDao.NameDao
import com.aristidevs.androidmaster.firstapp.Database.NameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): NameDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NameDatabase::class.java,
            "names_database"
        ).build()
    }

    @Provides
    fun provideNameDao(database: NameDatabase): NameDao {
        return database.nameDao()
    }

    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }
}