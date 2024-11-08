package ProyectoFinal.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "ventas")
data class Venta(
    @PrimaryKey(autoGenerate = true) val id: Int,          // ID único de la venta (Clave primaria)
    val clienteId: Int,                                    // ID del cliente (Clave foránea)
    val fecha: Date,                                       // Fecha de la venta
    val total: Double                                      // Monto total de la venta
)
