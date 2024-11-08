package com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroDataConverter

import androidx.room.TypeConverter
import java.util.Date

// Convierte entre Date y Long para que Room pueda almacenar fechas.
class DateConverter {

//a ver si cargo

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

}