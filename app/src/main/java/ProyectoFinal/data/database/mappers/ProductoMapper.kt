package ProyectoFinal.data.database.mappers

import ProyectoFinal.data.database.entities.Producto

object ProductoMapper {
    // Convierte una entidad Producto en un objeto de dominio Producto
    fun fromEntity(entity: Producto): Producto {
        return Producto(
            productoId = entity.productoId,
            nombre = entity.nombre,
            descripcion = entity.descripcion,
            preciounitario = entity.preciounitario,
            costounitario = entity.costounitario,
            cantidad = entity.cantidad,
            categoria = entity.categoria,
            fechaIngreso = entity.fechaIngreso,
            fechaVencimiento = entity.fechaVencimiento,
            proveedorId = entity.proveedorId,

            )
    }
    // Convierte un objeto Producto en una entidad Producto para la base de datos
    fun toEntity(domain: Producto): Producto {
        return Producto(
            productoId = domain.productoId,
            nombre = domain.nombre,
            descripcion = domain.descripcion,
            preciounitario = domain.preciounitario,
            costounitario = domain.costounitario,
            cantidad = domain.cantidad,
            categoria = domain.categoria,
            fechaIngreso = domain.fechaIngreso,
            fechaVencimiento = domain.fechaVencimiento,
            proveedorId = domain.proveedorId
        )
    }
}
