package ProyectoFinal.data.database.mappers

import ProyectoFinal.data.database.entities.Cliente

object ClienteMapper {
    // Convierte una entidad Cliente en un objeto de dominio Cliente
    fun fromEntity(entity: Cliente): Cliente {
        return Cliente(
            id = entity.id,
            nombre = entity.nombre,
            apellido = entity.apellido,
            email = entity.email,
            telefono = entity.telefono,
            fechaRegistro = entity.fechaRegistro
        )
    }

    // Convierte un objeto Cliente en una entidad para la base de datos
    fun toEntity(domain: Cliente): Cliente {
        return Cliente(
            id = domain.id,
            nombre = domain.nombre,
            apellido = domain.apellido,
            email = domain.email,
            telefono = domain.telefono,
            fechaRegistro = domain.fechaRegistro
        )
    }
}
