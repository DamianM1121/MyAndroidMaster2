package ProyectoFinal.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "productos")
data class Producto(
    @PrimaryKey(autoGenerate = false) val productoId: String,          // ID único del producto (Clave primaria)
    val nombre: String,                                    // Nombre del producto
    val descripcion: String,                               // Descripción breve del producto
    val preciounitario: Double,                                    // Precio unitario del producto
    val costounitario: Double,                                    // Precio unitario del producto
    val cantidad: Int,
    val categoria: String,
    val fechaIngreso: Date?,// Cantidad disponible en stock
    val fechaVencimiento: Date?,                           // Fecha de vencimiento (nullable si no aplica)
    val proveedorId: Int                                   // ID del proveedor (Clave foránea)
)
