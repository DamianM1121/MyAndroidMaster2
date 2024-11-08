package ProyectoFinal.data.database.dao

import ProyectoFinal.data.database.entities.Proveedor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProveedorDao {
    // Inserta un nuevo proveedor
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarProveedor(proveedor: Proveedor)

    // Obtiene todos los proveedores registrados
    @Query("SELECT * FROM proveedores")
    fun obtenerTodosLosProveedores(): Flow<List<Proveedor>>

    // Busca un proveedor por su ID
    @Query("SELECT * FROM proveedores WHERE cuit = :proveedorId")
    suspend fun obtenerProveedorPorId(proveedorId: Long): Proveedor?

    // Actualiza la información del proveedor
    @Update
    suspend fun actualizarProveedor(proveedor: Proveedor)

    // Elimina un proveedor
    @Delete
    suspend fun eliminarProveedor(proveedor: Proveedor)
}
