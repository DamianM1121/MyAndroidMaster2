package ProyectoFinal.data.database.dao

import ProyectoFinal.data.database.entities.Producto
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductoDao {
    // Inserta un nuevo producto en la base de datos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarProducto(producto: Producto)

    // Actualiza los datos de un producto existente
    @Update
    suspend fun actualizarProducto(producto: Producto)

    // Elimina un producto por su ID
    @Delete
    suspend fun eliminarProducto(producto: Producto)

    // Obtiene todos los productos almacenados
    @Query("SELECT * FROM productos")
    fun obtenerTodosLosProductos(): Flow<List<Producto>>

    // Busca un producto por su ID
    @Query("SELECT * FROM productos WHERE productoId = :productoId")
    suspend fun obtenerProductoPorId(productoId: String): Producto?

    // Busca un producto por su Categoria
    @Query("SELECT * FROM productos WHERE categoria = :categoria")
    suspend fun obtenerProductoPorCategoria(categoria: String): Producto?
}
