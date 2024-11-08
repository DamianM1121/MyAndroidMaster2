package ProyectoFinal.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "proveedores")
data class Proveedor(
    @PrimaryKey(autoGenerate = true) val cuit: Int,          // ID único del proveedor (Clave primaria)
    val nombre: String,                                    // Nombre del proveedor
    val contacto: String,                                  // Persona de contacto del proveedor
    val telefono: String,                                  // Teléfono de contacto
    val direccion: String,                                 // Dirección del proveedor
    val fechaRegistro: Date                                // Fecha en la que se registró el proveedor
)
