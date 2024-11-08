package com.aristidevs.androidmaster.firstapp.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aristidevs.androidmaster.firstapp.Database.DataConverter.LocalDateTimeConverter
import com.aristidevs.androidmaster.firstapp.Database.Entity.NameEntity
import com.aristidevs.androidmaster.firstapp.Database.NameDao.NameDao

@Database(entities = [NameEntity::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateTimeConverter::class) // Para convertir LocalDateTime
abstract class NameDatabase : RoomDatabase() {
    abstract fun nameDao(): NameDao
}