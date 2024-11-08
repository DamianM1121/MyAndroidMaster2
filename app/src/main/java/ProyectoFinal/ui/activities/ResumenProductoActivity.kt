package ProyectoFinal.ui.activities


import ProyectoFinal.viewmodels.ProductoViewModel
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aristidevs.androidmaster.databinding.ActivityResumenProductoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResumenProductoActivity : AppCompatActivity() {

    private val viewModel: ProductoViewModel by viewModels()
    private var productoId: String? = null // Cambia el tipo a String
    private lateinit var binding: ActivityResumenProductoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResumenProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtiene el ID del producto desde el intent y agrega un Log para verificar
        productoId = intent.getStringExtra("PRODUCTO_ID")
        Log.d("ResumenProductoActivity", "Producto ID recibido: $productoId")

        initListeners()
        updateProducto() // Cargar datos del producto seleccionado
    }

    private fun initListeners() {
        binding.btnEditar.setOnClickListener { navigateToEditarProducto() }
        binding.ibtnBalance.setOnClickListener { navigateToDashboard() }
        binding.ibtnDeudas.setOnClickListener { navigateToDeudas() }
        binding.ibtnInventario.setOnClickListener { navigateToInventario() }
    }

    private fun updateProducto() {
        // Verifica que el ID sea válido
        if (productoId != null) {
            Log.d("ResumenProductoActivity", "Iniciando carga de producto con ID: $productoId")
            // Observa el LiveData para obtener el producto por ID
            productoId?.let { productoId ->
                viewModel.getProductoById(productoId).observe(this) { producto ->
                    if (producto != null) {
                        Log.d("ResumenProductoActivity", "Producto obtenido: ${producto.nombre}")
                        // Asigna los valores del producto a los elementos de la vista
                        binding.tvCodigoProducto.text = producto.productoId
                        binding.tvNombreProducto.text = producto.nombre
                        binding.tvCantidadDisponible.text = producto.cantidad.toString()
                        binding.tvCostoUnitario.text = producto.costounitario.toString()
                        binding.tvPrecioUnitario.text = producto.preciounitario.toString()
                        binding.tvCategoria.text = producto.categoria
                    } else {
                        Log.d("ResumenProductoActivity", "Producto con ID $productoId no encontrado")
                        binding.tvNombreProducto.text = "Producto no encontrado"
                    }
                }
            }
        } else {
            Log.d("ResumenProductoActivity", "ID de producto inválido: $productoId")
            binding.tvNombreProducto.text = "ID de producto inválido"
        }
    }

    private fun navigateToEditarProducto() {
        val intent = Intent(this, EditarProductoActivity::class.java).apply {
            putExtra("PRODUCTO_ID", productoId)
        }
        Log.d("ResumenProductoActivity", "Navegando a EditarProductoActivity con ID: $productoId")
        startActivity(intent)
    }

    private fun navigateToDashboard() {
        val intent = Intent(this, StockActivity::class.java)
        Log.d("ResumenProductoActivity", "Navegando al Dashboard (StockActivity)")
        startActivity(intent)
    }

    private fun navigateToDeudas() {
        val intent = Intent(this, DeudasActivity::class.java)
        Log.d("ResumenProductoActivity", "Navegando a DeudasActivity")
        startActivity(intent)
    }

    private fun navigateToInventario() {
        val intent = Intent(this, StockActivity::class.java)
        Log.d("ResumenProductoActivity", "Navegando a Inventario (StockActivity)")
        startActivity(intent)
    }
}
