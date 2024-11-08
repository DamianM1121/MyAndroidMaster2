package ProyectoFinal.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "clientes")
data class Cliente(
    @PrimaryKey(autoGenerate = true) val id: Int,          // ID único del cliente (Clave primaria)
    val nombre: String,                                    // Nombre del cliente
    val apellido: String,                                  // Apellido del cliente
    val email: String,                                     // Correo electrónico
    val telefono: String,                                  // Número de teléfono
    val fechaRegistro: Date                                // Fecha en la que se registró el cliente
)
