package ProyectoFinal.ui.activities


import ProyectoFinal.data.database.ProyectoFinalDatabase
import ProyectoFinal.data.database.entities.Producto
import ProyectoFinal.data.database.repositories.ProductoRepository
import ProyectoFinal.data.database.repositories.factory.ProductoViewModelFactory
import ProyectoFinal.ui.adapter.CategoriaAdapter
import ProyectoFinal.ui.adapter.ProductoAdapter
import ProyectoFinal.ui.fragments.StockFragment
import ProyectoFinal.viewmodels.ProductoViewModel

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aristidevs.androidmaster.R
import com.aristidevs.androidmaster.databinding.ActivityStockBinding
import com.aristidevs.androidmaster.databinding.DialogProductoBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class StockActivity : AppCompatActivity(), ProductoAdapter.OnProductoClickListener {

    private lateinit var binding: ActivityStockBinding
    private lateinit var viewModel: ProductoViewModel
    private lateinit var categoriaAdapter: CategoriaAdapter
    private lateinit var productoAdapter: ProductoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa ViewModel y Repository
        val dao = ProyectoFinalDatabase.getDatabase(this).productoDao()
        val repository = ProductoRepository(dao)
        viewModel = ViewModelProvider(this, ProductoViewModelFactory(repository)).get(ProductoViewModel::class.java)

        // Observa cambios de cantidad y costo total
        viewModel.cantidadProductosLiveData.observe(this) { total ->
            binding.tvCantidadProductos.text = total.toString()
        }
        viewModel.costoTotalLiveData.observe(this) { total ->
            binding.tvCostoTotal.text = "$$total"
        }
        viewModel.categoriasLiveData.observe(this) { categorias ->
            categoriaAdapter.submitList(categorias)
        }

        // Configura RecyclerView
        setupRecyclerView()
        initListener()


    }

    // Listener para el botón de agregar producto
    private fun initListener() {
        navigateToDashboard()
        navigateToInventario()
        navigateToDeudas()
        binding.btnCargarProducto.setOnClickListener { showDialogProducto() }
    }

    // Configura el RecyclerView y el Adapter
    private fun setupRecyclerView() {
        productoAdapter = ProductoAdapter(this)  // Implementa OnProductoClickListener
        binding.rvProductos.apply {
            layoutManager = LinearLayoutManager(this@StockActivity)
            adapter = productoAdapter
        }

        // Observa productos y actualiza el adaptador
        viewModel.productos.observe(this) { productos ->
            productoAdapter.submitList(productos)
        }
    }

    // Diálogo para agregar un producto
    private fun showDialogProducto() {
        // Crear el diálogo con el estilo a pantalla completa
        val dialog = Dialog(this, R.style.FullScreenDialogStyle)  // Usamos el estilo aquí

        val dialogBinding = DialogProductoBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        var fechaVencimiento: Date? = null

        dialogBinding.etFechaVencimiento.setOnClickListener {
            val stockFragment = StockFragment { day, month, year ->
                val calendar = Calendar.getInstance().apply {
                    set(year, month, day)
                }
                fechaVencimiento = calendar.time
                dialogBinding.etFechaVencimiento.setText(
                    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(fechaVencimiento)
                )
            }
            stockFragment.show(supportFragmentManager, "stock_fragment")
        }

        dialogBinding.btnGuardar.setOnClickListener {
            val productoId = dialogBinding.etCodigoProducto.text.toString()
            val cantidad = dialogBinding.etCantidadProducto.text.toString().toIntOrNull() ?: 0
            val nombre = dialogBinding.etNombreProducto.text.toString()
            val precioUnitario = dialogBinding.etPrecioUnitario.text.toString().toDoubleOrNull() ?: 0.0
            val costoUnitario = dialogBinding.etCostoUnitario.text.toString().toDoubleOrNull() ?: 0.0
            val proveedor = dialogBinding.etProveedor.text.toString().toIntOrNull() ?: 0
            val categoria = dialogBinding.etCategoria.text.toString()

            if (productoId.isNotEmpty() && nombre.isNotEmpty() && fechaVencimiento != null) {
                val producto = Producto(
                    productoId = productoId,
                    nombre = nombre,
                    descripcion = "",
                    preciounitario = precioUnitario,
                    costounitario = costoUnitario,
                    cantidad = cantidad,
                    categoria   = categoria,
                    fechaIngreso = Date(),
                    fechaVencimiento = fechaVencimiento,
                    proveedorId = proveedor
                )
                viewModel.insertarProducto(producto)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.setCancelable(true)
        dialog.show()
    }

    private fun navigateToDashboard() {

        // Configurar el botón para navegar a stockActivity
        binding.ibtnBalance.setOnClickListener {
            val intent = Intent(this, StockActivity::class.java)
            startActivity(intent)
        }
    }

    private fun navigateToDeudas() {

        // Configurar el botón para navegar a DeudasActivity
        binding.ibtnDeudas.setOnClickListener {
            val intent = Intent(this, DeudasActivity::class.java)
            startActivity(intent)

        }
    }

    private fun navigateToInventario() {

        // Configurar el botón para navegar a stockActivity
        binding.ibtnInventario.setOnClickListener {
            val intent = Intent(this, StockActivity::class.java)
            startActivity(intent)
        }
    }

    // Navega a la pantalla de ResumenProductoActivity al hacer clic en un producto
    override fun onProductoClick(producto: Producto) {
        val intent = Intent(this, ResumenProductoActivity::class.java).apply {
            putExtra("PRODUCTO_ID", producto.productoId)
        }
        startActivity(intent)
    }

}
