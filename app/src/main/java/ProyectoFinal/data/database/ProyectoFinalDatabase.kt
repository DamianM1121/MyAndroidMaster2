package ProyectoFinal.data.database

import ProyectoFinal.data.database.dao.ClienteDao
import ProyectoFinal.data.database.dao.CompraDao
import ProyectoFinal.data.database.dao.ProductoDao
import ProyectoFinal.data.database.dao.ProveedorDao
import ProyectoFinal.data.database.dao.VentaDao
import ProyectoFinal.data.database.entities.Cliente
import ProyectoFinal.data.database.entities.Compra
import ProyectoFinal.data.database.entities.Producto
import ProyectoFinal.data.database.entities.Proveedor
import ProyectoFinal.data.database.entities.Venta
import ProyectoFinal.utils.DateUtil
import android.content.Context
import android.text.format.DateUtils
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroDataConverter.DateConverter

// Clase Database con varias entidades
@Database(
    entities = [Producto::class, Proveedor::class, Venta::class, Compra::class, Cliente::class],
    version = 1
)
@TypeConverters(DateUtil::class)
abstract class ProyectoFinalDatabase : RoomDatabase() {
    abstract fun productoDao(): ProductoDao
    abstract fun proveedorDao(): ProveedorDao
    abstract fun ventaDao(): VentaDao
    abstract fun compraDao(): CompraDao
    abstract fun clienteDao(): ClienteDao

    companion object {
        @Volatile
        private var INSTANCE: ProyectoFinalDatabase? = null

        fun getDatabase(context: Context): ProyectoFinalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProyectoFinalDatabase::class.java,
                    "proyecto_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}