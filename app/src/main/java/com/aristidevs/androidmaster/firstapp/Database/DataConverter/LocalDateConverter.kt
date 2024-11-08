package com.aristidevs.androidmaster.firstapp.Database.DataConverter

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object LocalDateTimeConverter {

    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    @TypeConverter
    fun fromString(value: String?): Date? {
        return value?.let { formatter.parse(it) }
    }

    @TypeConverter
    fun toString(date: Date?): String? {
        return date?.let { formatter.format(it) }
    }
}