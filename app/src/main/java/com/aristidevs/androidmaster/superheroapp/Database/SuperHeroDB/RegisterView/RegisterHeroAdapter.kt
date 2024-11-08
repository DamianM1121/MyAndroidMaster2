package com.aristidevs.androidmaster.superheroapp.Database.SuperHeroDB.RegisterView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aristidevs.androidmaster.R
import com.aristidevs.androidmaster.databinding.ActivityRegisterHeroBinding
import com.aristidevs.androidmaster.databinding.RegisterViewDataBinding
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroEntity.SearchHistoryEntity
import com.squareup.picasso.Picasso

//// Adapter para manejar los datos del héroe en el RecyclerView
class RegisterHeroAdapter(
    private var heroes: List<SearchHistoryEntity>// Lista de registros de héroes
) : RecyclerView.Adapter<RegisterHeroAdapter.HeroViewHolder>() {

    //    // ViewHolder que enlaza las vistas con los datos utilizando binding
    inner class HeroViewHolder(private val binding: RegisterViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //        // Método para bindear los datos a cada vista del item
        fun bind(hero: SearchHistoryEntity) {


//            // Seteamos los datos en los TextView correspondientes
            binding.tvNameRegister.text = hero.superheroName
            binding.tvDateRegister.text = hero.timestamp.toString() // Fecha en String
            binding.tvConsultas.text =
                "Consultado: ${hero.timesQueried} veces" // Número de consultas

            Picasso.get().load(hero.imageRegister)
                .placeholder(R.drawable.ic_superhero_placeholder)// Placeholder mientras carga
                .error(R.drawable.ic_error)// Imagen en caso de error
                .into(binding.ivSuperheroRegister)// ImageView donde se mostrará


        }
    }

    //    // Inflamos la vista del item usando ViewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val binding = RegisterViewDataBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HeroViewHolder(binding)
    }

    //    // Vinculamos los datos con el ViewHolder
    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(heroes[position])
    }

    //    // Devuelve el tamaño de la lista
    override fun getItemCount(): Int = heroes.size

    fun updateHeroes(newHeroes: List<SearchHistoryEntity>) {
        this.heroes = newHeroes
        notifyDataSetChanged() // Actualiza la lista y notifica al RecyclerView
    }
}
