package com.aristidevs.androidmaster.superheroapp.Database.SuperHeroDB.RegisterView

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.aristidevs.androidmaster.R
import com.aristidevs.androidmaster.databinding.ActivityRegisterHeroBinding
import com.aristidevs.androidmaster.databinding.ActivitySuperHeroListBinding
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroDao.SearchHistoryDao
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroEntity.SearchHistoryEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

private lateinit var binding: ActivityRegisterHeroBinding
private lateinit var adapter: RegisterHeroAdapter

@AndroidEntryPoint
class RegisterHeroActivity : AppCompatActivity() {

    // Inyección del DAO para acceder a la base de datos.
    @Inject
    lateinit var searchHistoryDao: SearchHistoryDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Agrega los datos al recyclerview

        // Llamamos al método para cargar los héroes al iniciar la actividad.
        loadHeroesFromDatabase()

        listenerButton()
    }

    private fun listenerButton(){
        binding.btnClearRegister.setOnClickListener {
            clearAllHeroes()
        }
    }

    private fun clearAllHeroes() {
        lifecycleScope.launch {
            try {
                searchHistoryDao.clearAll()  // Método para borrar todo
                adapter.updateHeroes(emptyList())  // Limpia la lista en el adapter
                Log.i("RegisterHeroActivity", "Registros eliminados exitosamente.")
            } catch (e: Exception) {
                Log.e("RegisterHeroActivity", "Error al eliminar registros", e)
            }
        }
    }

    private fun setupRecyclerView(heroes: List<SearchHistoryEntity>) {
        adapter = RegisterHeroAdapter(heroes) // Inicializamos el adapter
        binding.rvRegisterViewHeros.layoutManager = LinearLayoutManager(this)
        binding.rvRegisterViewHeros.adapter = adapter
    }

    private fun loadHeroesFromDatabase() {
        lifecycleScope.launch {
            try {
                // Obtenemos la lista de héroes desde el DAO.
                val heroesList: List<SearchHistoryEntity> = searchHistoryDao.getAllSearchHistory()

                if (heroesList.isNotEmpty()) {
                    // Mostramos la lista de héroes en el log por ahora.
                    for (hero in heroesList) {
                        Log.i(
                            "RegisterHeroActivity",
                            "Héroe: ${hero.superheroName}, TimeSquared: ${hero.timesQueried}"
                        )
                    }
                    setupRecyclerView(heroesList)
                    // Aquí podrías actualizar la interfaz con los datos si es necesario.
                } else {
                    Log.i("RegisterHeroActivity", "No hay héroes guardados.")
                }
            } catch (e: Exception) {
                Log.e("RegisterHeroActivity", "Error al cargar héroes", e)
            }
        }
    }
}

