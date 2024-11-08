package ProyectoFinal.data.database.repositories.factory

import ProyectoFinal.data.database.repositories.ProductoRepository
import ProyectoFinal.viewmodels.ProductoViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProductoViewModelFactory(
    private val repository: ProductoRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductoViewModel::class.java)) {
            return ProductoViewModel(repository) as T
        }
        throw IllegalArgumentException("Clase ViewModel Desconocida")
    }
}