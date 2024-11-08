package ProyectoFinal.data.database.repositories

import ProyectoFinal.data.database.dao.ClienteDao
import ProyectoFinal.data.database.entities.Cliente
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClienteRepository @Inject constructor(
    private val clienteDao: ClienteDao
) {
    // Inserta un cliente
    suspend fun insertarCliente(cliente: Cliente) {
        clienteDao.insertarCliente(cliente)
    }

    // Actualiza un cliente
    suspend fun actualizarCliente(cliente: Cliente) {
        clienteDao.actualizarCliente(cliente)
    }

    // Elimina un cliente
    suspend fun eliminarCliente(cliente: Cliente) {
        clienteDao.eliminarCliente(cliente)
    }

    // Obtiene todos los clientes como flujo
    fun obtenerTodosLosClientes(): Flow<List<Cliente>> {
        return clienteDao.obtenerTodosLosClientes()
    }

    // Busca un cliente por su ID
    suspend fun obtenerClientePorId(clienteId: Long): Cliente? {
        return clienteDao.obtenerClientePorId(clienteId)
    }
}
