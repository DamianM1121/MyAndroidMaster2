package com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroEntity.SearchHistoryEntity

// DAO que contiene los métodos de acceso a los datos.
@Dao
interface SearchHistoryDao {
    //en caso de que se cree un mismo valor se reemplaza
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchHistory(searchHistory: List<SearchHistoryEntity>)

    //seleccionaria  todo de s_history y ordenalo por la fecha en descendente
    @Query("SELECT * FROM search_history ORDER BY timestamp DESC")
    suspend fun getAllSearchHistory(): List<SearchHistoryEntity>

    //selecionaria tod0 del historial definiendo el id en 1
    @Query("SELECT * FROM search_history WHERE id = :id LIMIT 1")
    suspend fun getHeroById(id: Long): SearchHistoryEntity?

    @Query("UPDATE search_history SET timesQueried = timesQueried + 1 WHERE id = :id")
    suspend fun incrementTimesQueried(id: Long)

    @Query("DELETE FROM search_history")
    suspend fun clearAll()
}
