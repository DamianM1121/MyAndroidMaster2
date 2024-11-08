package com.aristidevs.androidmaster.superheroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.core.view.isVisible
import com.aristidevs.androidmaster.R
import com.aristidevs.androidmaster.databinding.ActivityDetailSuperheroBinding
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroDao.SearchHistoryDao
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroEntity.SearchHistoryEntity
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.util.Date
import javax.inject.Inject
import kotlin.math.roundToInt

// Marcamos la actividad con @AndroidEntryPoint para que Hilt pueda inyectar dependencias.
@AndroidEntryPoint
class DetailSuperheroActivity : AppCompatActivity() {

    // Inyección del DAO utilizando Hilt para acceder al historial.
    @Inject
    lateinit var searchHistoryDao: SearchHistoryDao

    // Constante que define la clave para el ID que se pasa a esta actividad mediante un Intent.
    companion object {
        const val EXTRA_ID = "extra_id"
    }

    // Variable para gestionar el binding con las vistas del layout.
    private lateinit var binding: ActivityDetailSuperheroBinding

    // Método que se ejecuta al crear la actividad.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializamos el binding para acceder a las vistas.
        binding = ActivityDetailSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)// Establecemos la vista raíz del layout.

        // Obtenemos el ID del superhéroe desde el Intent.
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        // Llamamos al método para obtener los detalles del héroe usando el ID.
        getSuperheroInformation(id)
    }

    // Método para realizar una llamada a la API y obtener la información del héroe.
    private fun getSuperheroInformation(id: String) {
        // Ejecutamos la llamada en un hilo secundario utilizando una Coroutine.
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Realizamos la llamada a la API para obtener los detalles del héroe.
                val response: Response<SuperHeroDetailResponse> =
                    getRetrofit().create(ApiService::class.java).getSuperheroDetail(id)

                // Verificamos si la respuesta fue exitosa y contiene datos.
                if (response.isSuccessful && response.body() != null) {
                    val superheroDetail = response.body()!!  // Obtenemos el cuerpo de la respuesta.

                    // Guardamos los datos del héroe en el historial.
                    saveHeroInHistory(superheroDetail)

                    // Actualizamos la UI en el hilo principal.
                    runOnUiThread { createUI(superheroDetail) }
                } else {
                    Log.e(
                        "SuperHeroApp",
                        "Error en la respuesta de la API: ${response.errorBody()?.string()}"
                    )
                }
            } catch (e: Exception) {
                // Manejo de excepciones en caso de error en la llamada o parsing.
                Log.e("SuperHeroApp", "Error al obtener información del héroe", e)
            }
        }
    }


    // Método para guardar los detalles del héroe en la base de datos.
    private suspend fun saveHeroInHistory(superhero: SuperHeroDetailResponse) {

        // Verificar si el héroe ya existe en la base de datos.
        val existingHero = searchHistoryDao.getHeroById(superhero.superheroDetailId.toLong())

        if (existingHero != null) {
            // Incrementamos TimeSquared si el héroe ya existe.
            searchHistoryDao.incrementTimesQueried(superhero.superheroDetailId.toLong())
            Log.i("SuperHeroApp", "Héroe actualizado: ${superhero.name}, TimeSquared incrementado.")
        } else {

            // Creamos una entidad con los datos del héroe para almacenar en la base de datos.
            val searchHistoryEntity = SearchHistoryEntity(
                id = superhero.superheroDetailId.toLong(),  // Usamos el ID de la API como ID en la base de datos.
                superheroName = superhero.name,  // Guardamos el nombre del héroe.
                timestamp = Date(),  // Registramos la fecha y hora actual.
                timesQueried = 1, // Iniciar con 1 si es la primera vez.
                imageRegister = superhero.image.url
            )

            // Insertamos la entidad en la base de datos utilizando el DAO.
            searchHistoryDao.insertSearchHistory(listOf(searchHistoryEntity))

            // Registramos en el log que el héroe se ha guardado correctamente.
            Log.i("SuperHeroApp", "Héroe guardado en el historial: ${superhero.name}")
        }
    }

    // Método para crear la interfaz de usuario con los datos del héroe.
    private fun createUI(superhero: SuperHeroDetailResponse) {
        // Cargamos la imagen del héroe desde la URL usando Picasso.
        Picasso.get().load(superhero.image.url).into(binding.ivSuperhero)
        // Mostramos el nombre del héroe.
        binding.tvSuperheroName.text = superhero.name
        // Mostramos las estadísticas del héroe en la UI.
        prepareStats(superhero.powerstats)
        // Mostramos el nombre real del personaje.
        binding.tvSuperheroRealName.text = superhero.biography.fullName
        // Mostramos la editorial del héroe (por ejemplo, Marvel o DC).
        binding.tvPublisher.text = superhero.biography.publisher
    }

    // Método para preparar las estadísticas del héroe y mostrarlas en la UI.
    private fun prepareStats(powerstats: PowerStatsResponse) {
        // Ajustamos la altura de las vistas según los valores de las estadísticas.
        updateHeight(binding.viewCombat, powerstats.combat)
        updateHeight(binding.viewDurability, powerstats.durability)
        updateHeight(binding.viewSpeed, powerstats.speed)
        updateHeight(binding.viewStrength, powerstats.strength)
        updateHeight(binding.viewIntelligence, powerstats.intelligence)
        updateHeight(binding.viewPower, powerstats.power)
    }

    // Método para actualizar la altura de una vista basada en el valor de una estadística.
    private fun updateHeight(view: View, stat: String) {
        // Obtenemos los parámetros de la vista.
        val params = view.layoutParams
        // Convertimos el valor de la estadística a píxeles y lo asignamos como altura.
        params.height = pxToDp(stat.toFloat())
        // Aplicamos los nuevos parámetros a la vista.
        view.layoutParams = params
    }

    // Método para convertir un valor en píxeles a DP (density-independent pixels).
    private fun pxToDp(px: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            px,
            resources.displayMetrics
        )
            .roundToInt()// Redondeamos el valor a un número entero.
    }

    // Método para crear una instancia de Retrofit, necesaria para las llamadas a la API.
    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")// URL base de la API.
            .addConverterFactory(GsonConverterFactory.create())// Convertidor de JSON a objetos.
            .build()// Construimos la instancia de Retrofit.
    }
}

