package ProyectoFinal.viewmodels

import ProyectoFinal.data.database.entities.Producto
import ProyectoFinal.data.database.repositories.ProductoRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductoViewModel@Inject constructor(
    private val repository: ProductoRepository
) : ViewModel() {

    val losProductos: LiveData<List<Producto>> = repository.obtenerTodosLosProductos().asLiveData()

    private val _cantidadProductosLiveData = MediatorLiveData<Int>()
    val cantidadProductosLiveData: LiveData<Int> get() = _cantidadProductosLiveData

    private val _costoTotalLiveData = MediatorLiveData<Double>()
    val costoTotalLiveData: LiveData<Double> get() = _costoTotalLiveData

    private val _productosPorCategoriaLiveData = MediatorLiveData<List<Producto>>()
    val productosPorCategoriaLiveData: LiveData<List<Producto>> get() = _productosPorCategoriaLiveData


    init {
        // Actualizar los totales cada vez que cambie la lista de productos
        _cantidadProductosLiveData.addSource(losProductos) { listaProductos ->
            _cantidadProductosLiveData.value = listaProductos?.sumOf { it.cantidad } ?: 0
            _costoTotalLiveData.value = listaProductos?.sumOf { it.cantidad * it.preciounitario } ?: 0.0
            Log.d("ProductoViewModel", "Total de productos calculado: ${_cantidadProductosLiveData.value}")
            Log.d("ProductoViewModel", "Costo total calculado: ${_costoTotalLiveData.value}")
        }
    }

    fun insertarProducto(producto: Producto) {
        viewModelScope.launch {
            repository.insertarProducto(producto)
            Log.d("Producto", "Producto insertado con éxito")
        }
    }

    fun getProductoById(id: String): LiveData<Producto?> = liveData {
        Log.d("ProductoViewModel", "Buscando producto con ID (String): $id")
        val producto = repository.obtenerProductoPorId(id)
        emit(producto)
    }

    fun getProductoByCategory(category: String): LiveData<List<Producto>> = liveData {
        Log.d("ProductoViewModel", "Buscando producto con ID (String): $category")
//        val productosPorCategoria = repository.obtenerProductoPorCategoria(category)
//        emit(productosPorCategoria)
    }

    fun actualizarProducto(producto: Producto) {
        viewModelScope.launch {
            repository.actualizarProducto(producto)
            Log.d("Producto", "Producto actualizado con éxito")
        }
    }

    fun eliminarProducto(producto: Producto) {
        viewModelScope.launch {
            repository.eliminarProducto(producto)
            Log.d("Producto", "Producto eliminado con éxito")
        }
    }

//    fun setCategory(category: String) {
//        val productosLiveData = getProductosByCategory(category)
//        _productosPorCategoriaLiveData.addSource(productosLiveData) { productos ->
//            _productosPorCategoriaLiveData.value = productos
//            // Puedes eliminar la fuente una vez que los datos se obtengan, para evitar actualizaciones redundantes:
//            _productosPorCategoriaLiveData.removeSource(productosLiveData)
//        }
//    }

    val productos: LiveData<List<Producto>> = repository.obtenerTodosLosProductos().asLiveData()
}
