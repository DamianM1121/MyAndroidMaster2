package ProyectoFinal.data.database.dao

import ProyectoFinal.data.database.entities.Venta
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VentaDao {
    // Inserta una nueva venta en la base de datos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarVenta(venta: Venta)

    // Obtiene todas las ventas realizadas
    @Query("SELECT * FROM ventas ORDER BY fecha DESC")
    fun obtenerTodasLasVentas(): Flow<List<Venta>>

    // Obtiene ventas realizadas por un cliente específico
    @Query("SELECT * FROM ventas WHERE clienteId = :clienteId")
    fun obtenerVentasPorCliente(clienteId: Long): Flow<List<Venta>>

    // Elimina una venta específica
    @Delete
    suspend fun eliminarVenta(venta: Venta)
}
