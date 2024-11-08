package ProyectoFinal.data.database.repositories

import ProyectoFinal.data.database.dao.VentaDao
import ProyectoFinal.data.database.entities.Venta
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VentaRepository @Inject constructor(
    private val ventaDao: VentaDao
) {
    // Inserta una venta
    suspend fun insertarVenta(venta: Venta) {
        ventaDao.insertarVenta(venta)
    }

    // Elimina una venta específica
    suspend fun eliminarVenta(venta: Venta) {
        ventaDao.eliminarVenta(venta)
    }

    // Obtiene todas las ventas como flujo
    fun obtenerTodasLasVentas(): Flow<List<Venta>> {
        return ventaDao.obtenerTodasLasVentas()
    }

    // Obtiene las ventas realizadas por un cliente
    fun obtenerVentasPorCliente(clienteId: Long): Flow<List<Venta>> {
        return ventaDao.obtenerVentasPorCliente(clienteId)
    }
}
