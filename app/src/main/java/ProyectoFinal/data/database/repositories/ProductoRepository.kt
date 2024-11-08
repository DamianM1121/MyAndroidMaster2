package ProyectoFinal.data.database.repositories

import ProyectoFinal.data.database.dao.ProductoDao
import ProyectoFinal.data.database.entities.Producto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductoRepository @Inject constructor(
    private val productoDao: ProductoDao
) {
    // Inserta un producto
    suspend fun insertarProducto(producto: Producto) {
        productoDao.insertarProducto(producto)
    }

    // Actualiza un producto
    suspend fun actualizarProducto(producto: Producto) {
        productoDao.actualizarProducto(producto)
    }

    // Elimina un producto
    suspend fun eliminarProducto(producto: Producto) {
        productoDao.eliminarProducto(producto)
    }

    // Obtiene todos los productos como un flujo
    fun obtenerTodosLosProductos(): Flow<List<Producto>> {
        return productoDao.obtenerTodosLosProductos()
    }

    // Busca un producto por su ID
    suspend fun obtenerProductoPorId(productoId: String): Producto? {
        return productoDao.obtenerProductoPorId(productoId)
    }

    // Busca un producto por su ID
    suspend fun obtenerProductoPorCategoria(categoria: String): Producto? {
        return productoDao.obtenerProductoPorCategoria(categoria)
    }
}

