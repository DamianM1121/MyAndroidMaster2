package ProyectoFinal.data.database.module

import ProyectoFinal.data.database.ProyectoFinalDatabase
import ProyectoFinal.data.database.dao.ClienteDao
import ProyectoFinal.data.database.dao.CompraDao
import ProyectoFinal.data.database.dao.ProductoDao
import ProyectoFinal.data.database.dao.ProveedorDao
import ProyectoFinal.ui.activities.DeudasActivity
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ProyectoFinalDatabase {
        return Room.databaseBuilder(
            context,
            ProyectoFinalDatabase::class.java,
            "proyecto_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideClienteDao(database: ProyectoFinalDatabase): ClienteDao {
        return database.clienteDao()
    }

    @Singleton
    @Provides
    fun provideCompraDao(database: ProyectoFinalDatabase): CompraDao {
        return database.compraDao()
    }

    @Singleton
    @Provides
    fun provideProductoDao(database: ProyectoFinalDatabase): ProductoDao {
        return database.productoDao()
    }

    @Singleton
    @Provides
    fun provideProveedorDao(database: ProyectoFinalDatabase): ProveedorDao {
        return database.proveedorDao()
    }


}
