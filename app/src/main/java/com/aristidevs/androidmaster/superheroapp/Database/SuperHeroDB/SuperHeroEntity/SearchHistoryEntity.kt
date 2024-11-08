package com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroEntity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aristidevs.androidmaster.superheroapp.SuperheroImageDetailResponse
import com.aristidevs.androidmaster.superheroapp.SuperheroImageResponse
import com.aristidevs.androidmaster.superheroapp.SuperheroItemResponse
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

@Entity(tableName = "search_history")
data class SearchHistoryEntity(
    @PrimaryKey(autoGenerate = false) val id: Long, // ID autogenerado
    //val superheroId: String, // ID del superhéroe
    val superheroName: String, // Nombre del superhéroe
    val timestamp: Date, // Marca de tiempo para cuando se realizó la búsqued
    val timesQueried: Int = 1, // Contador de Consultas
    val imageRegister: String // URL de la imagen del superhéroe
)
