package com.aristidevs.androidmaster.firstapp.Database.NameDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.aristidevs.androidmaster.firstapp.Database.Entity.NameEntity

@Dao
interface NameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertName(name: NameEntity)
}