package ProyectoFinal.data.database.mappers

import ProyectoFinal.data.database.entities.Venta

object VentaMapper {
    // Convierte una entidad Venta en un objeto de dominio Venta
    fun fromEntity(entity: Venta): Venta {
        return Venta(
            id = entity.id,
            clienteId = entity.clienteId,
            total = entity.total,
            fecha = entity.fecha
        )
    }

    // Convierte un objeto Venta en una entidad para la base de datos
    fun toEntity(domain: Venta): Venta {
        return Venta(
            id = domain.id,
            clienteId = domain.clienteId,
            total = domain.total,
            fecha = domain.fecha
        )
    }
}
