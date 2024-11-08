package ProyectoFinal.data.database.mappers

import ProyectoFinal.data.database.entities.Proveedor

object ProveedorMapper {
    // Convierte una entidad Proveedor en un objeto de dominio Proveedor
    fun fromEntity(entity: Proveedor): Proveedor {
        return Proveedor(
            cuit = entity.cuit,
            nombre = entity.nombre,
            contacto = entity.contacto,
            telefono = entity.telefono,
            direccion = entity.direccion,
            fechaRegistro = entity.fechaRegistro
        )
    }

    // Convierte un objeto Proveedor en una entidad para la base de datos
    fun toEntity(domain: Proveedor): Proveedor {
        return Proveedor(
            cuit = domain.cuit,
            nombre = domain.nombre,
            contacto = domain.contacto,
            telefono = domain.telefono,
            direccion = domain.direccion,
            fechaRegistro = domain.fechaRegistro
        )
    }
}
