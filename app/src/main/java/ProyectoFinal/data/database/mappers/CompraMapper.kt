package ProyectoFinal.data.database.mappers

import ProyectoFinal.data.database.entities.Compra

object CompraMapper {
    // Convierte una entidad Compra en un objeto de dominio Compra
    fun fromEntity(entity: Compra): Compra {
        return Compra(
            id = entity.id,
            proveedorId = entity.proveedorId,
            total = entity.total,
            fecha = entity.fecha
        )
    }

    // Convierte un objeto Compra en una entidad para la base de datos
    fun toEntity(domain: Compra): Compra {
        return Compra(
            id = domain.id,
            proveedorId = domain.proveedorId,
            total = domain.total,
            fecha = domain.fecha
        )
    }
}
