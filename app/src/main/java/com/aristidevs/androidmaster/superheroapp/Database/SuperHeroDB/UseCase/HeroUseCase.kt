package com.aristidevs.androidmaster.superheroapp.Database.SuperHeroDB.UseCase

import com.aristidevs.androidmaster.sintaxis.SuperHero
import com.aristidevs.androidmaster.superheroapp.ApiService
import com.aristidevs.androidmaster.superheroapp.Database.SuperHeroDB.HeroSearchHistory
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroDao.SearchHistoryDao
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroEntity.SearchHistoryEntity
import java.util.Date
import javax.inject.Inject

class HeroUseCase @Inject constructor(
    private val apiService: ApiService,
    private val searchHistoryDao: SearchHistoryDao,
    private val mapper: HeroMapper,
    private val dao: SearchHistoryDao
) {

    // Busca héroes por nombre desde la API y guarda los resultados en la base de datos
    suspend fun searchHeroesByName(name: String): List<HeroSearchHistory> {
        val response = apiService.getSuperheroes(name)

        if (response.isSuccessful) {
            // Obtiene la lista de héroes
            val heroes = response.body()?.superheroes ?: emptyList()
            // Mapea cada héroe a una entidad para almacenarlo en la base de datos
            val entities = heroes.map { mapper.mapToEntity(it) }

            // Guardamos los héroes en la base de datos
            searchHistoryDao.insertSearchHistory(entities)

            // Retornamos la lista de héroes mapeados a objetos de dominio
            return entities.map { mapper.mapToDomain(it) }
        } else {
            throw Exception("Error al obtener héroes desde la API: ${response.errorBody()}")
        }
    }

    // Obtiene el historial de búsqueda desde la base de datos
    suspend fun getSearchHistory(): List<HeroSearchHistory> {
        val entities = searchHistoryDao.getAllSearchHistory()
        return entities.map { mapper.mapToDomain(it) }
    }

    suspend fun getHeroById(id: String): SearchHistoryEntity? {
        return dao.getHeroById(id.toLong())
    }
}
