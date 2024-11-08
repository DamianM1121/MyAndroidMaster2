package ProyectoFinal.utils

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateUtil {
    private val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

    // De Date a String
    @TypeConverter
    fun fromDate(date: Date?): String? {
        return date?.let { dateFormat.format(it) }
    }

    // De String a Date
    @TypeConverter
    fun toDate(dateString: String?): Date? {
        return dateString?.let { dateFormat.parse(it) }
    }
}
