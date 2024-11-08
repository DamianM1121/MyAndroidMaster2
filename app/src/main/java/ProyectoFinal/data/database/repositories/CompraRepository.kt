package ProyectoFinal.data.database.repositories

import ProyectoFinal.data.database.dao.CompraDao
import ProyectoFinal.data.database.entities.Compra
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CompraRepository @Inject constructor(
    private val compraDao: CompraDao
) {
    // Inserta una compra
    suspend fun insertarCompra(compra: Compra) {
        compraDao.insertarCompra(compra)
    }

    // Elimina una compra específica
    suspend fun eliminarCompra(compra: Compra) {
        compraDao.eliminarCompra(compra)
    }

    // Obtiene todas las compras como flujo
    fun obtenerTodasLasCompras(): Flow<List<Compra>> {
        return compraDao.obtenerTodasLasCompras()
    }

    // Obtiene compras realizadas a un proveedor específico
    fun obtenerComprasPorProveedor(proveedorId: Long): Flow<List<Compra>> {
        return compraDao.obtenerComprasPorProveedor(proveedorId)
    }
}
