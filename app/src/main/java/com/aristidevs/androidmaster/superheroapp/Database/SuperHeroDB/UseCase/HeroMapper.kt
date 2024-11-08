package com.aristidevs.androidmaster.superheroapp.Database.SuperHeroDB.UseCase

import com.aristidevs.androidmaster.superheroapp.Database.SuperHeroDB.HeroSearchHistory
import com.aristidevs.androidmaster.superheroapp.SuperHeroDataResponse
import com.aristidevs.androidmaster.superheroapp.SuperheroItemResponse
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroEntity.SearchHistoryEntity
import java.time.LocalDateTime
import java.util.Date

class HeroMapper {

    // Convierte una entidad de la base de datos a un objeto de dominio
    fun mapToDomain(entity: SearchHistoryEntity): HeroSearchHistory {
        return HeroSearchHistory(
            heroName = entity.superheroName,
            queryTime = entity.timestamp
        )
    }

    // Convierte un objeto de respuesta de la API a una entidad para la base de datos
    fun mapToEntity(response: SuperheroItemResponse): SearchHistoryEntity {
        return SearchHistoryEntity(
            id = response.superheroId.toLong(),
            superheroName = response.name,
            timestamp = Date(),// Registra la hora actual
            imageRegister = response.superheroImage.url
        )
    }
}

