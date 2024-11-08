package ProyectoFinal.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "compras")
data class Compra(
    @PrimaryKey(autoGenerate = true) val id: Int,          // ID único de la compra (Clave primaria)
    val proveedorId: Int,                                  // ID del proveedor (Clave foránea)
    val fecha: Date,                                       // Fecha de la compra
    val total: Double                                      // Monto total de la compra
)
