package ProyectoFinal.data.database.dao

import ProyectoFinal.data.database.entities.Cliente
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDao {
    // Inserta un nuevo cliente
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarCliente(cliente: Cliente)

    // Obtiene todos los clientes registrados
    @Query("SELECT * FROM clientes")
    fun obtenerTodosLosClientes(): Flow<List<Cliente>>

    // Busca un cliente por su ID
    @Query("SELECT * FROM clientes WHERE id = :clienteId")
    suspend fun obtenerClientePorId(clienteId: Long): Cliente?

    // Actualiza la información de un cliente
    @Update
    suspend fun actualizarCliente(cliente: Cliente)

    // Elimina un cliente
    @Delete
    suspend fun eliminarCliente(cliente: Cliente)
}
