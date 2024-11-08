package ProyectoFinal.data.database.repositories

import ProyectoFinal.data.database.dao.ProveedorDao
import ProyectoFinal.data.database.entities.Proveedor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProveedorRepository @Inject constructor(
    private val proveedorDao: ProveedorDao
) {
    // Inserta un proveedor
    suspend fun insertarProveedor(proveedor: Proveedor) {
        proveedorDao.insertarProveedor(proveedor)
    }

    // Actualiza un proveedor
    suspend fun actualizarProveedor(proveedor: Proveedor) {
        proveedorDao.actualizarProveedor(proveedor)
    }

    // Elimina un proveedor
    suspend fun eliminarProveedor(proveedor: Proveedor) {
        proveedorDao.eliminarProveedor(proveedor)
    }

    // Obtiene todos los proveedores como flujo
    fun obtenerTodosLosProveedores(): Flow<List<Proveedor>> {
        return proveedorDao.obtenerTodosLosProveedores()
    }

    // Busca un proveedor por su ID
    suspend fun obtenerProveedorPorId(proveedorId: Long): Proveedor? {
        return proveedorDao.obtenerProveedorPorId(proveedorId)
    }
}
