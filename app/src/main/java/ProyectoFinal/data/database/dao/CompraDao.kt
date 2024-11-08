package ProyectoFinal.data.database.dao

import ProyectoFinal.data.database.entities.Compra
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CompraDao {
    // Inserta una nueva compra en la base de datos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarCompra(compra: Compra)

    // Obtiene todas las compras realizadas
    @Query("SELECT * FROM compras ORDER BY fecha DESC")
    fun obtenerTodasLasCompras(): Flow<List<Compra>>

    // Obtiene compras realizadas a un proveedor específico
    @Query("SELECT * FROM compras WHERE proveedorId = :proveedorId")
    fun obtenerComprasPorProveedor(proveedorId: Long): Flow<List<Compra>>

    // Elimina una compra específica
    @Delete
    suspend fun eliminarCompra(compra: Compra)
}
