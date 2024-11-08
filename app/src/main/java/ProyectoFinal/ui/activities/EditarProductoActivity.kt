package ProyectoFinal.ui.activities

import ProyectoFinal.data.database.entities.Producto
import ProyectoFinal.ui.fragments.EditarProductoFragment
import ProyectoFinal.viewmodels.ProductoViewModel
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aristidevs.androidmaster.databinding.ActivityEditarProductoBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class EditarProductoActivity : AppCompatActivity() {

    // Variables globales de la clase
    private var newFechaVencimiento: Date? = null
    private val viewModel: ProductoViewModel by viewModels()
    private var productoId: String? = null // ID del producto a editar
    private lateinit var binding: ActivityEditarProductoBinding // Binding para acceder a las vistas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtiene el ID del producto desde el intent y agrega un Log para verificar
        productoId = intent.getStringExtra("PRODUCTO_ID")
        Log.d("EditarProductoActivity", "Producto ID recibido: $productoId")

        // Inicializa los listeners y la carga de datos
        initListeners()
    }

    /**
     * Inicializa todos los listeners necesarios para las interacciones del usuario.
     */
    private fun initListeners() {
        setupFechaVencimientoListener() // Configura el listener para la selección de fecha
        rechargeProducto()               // Carga el producto existente para editar
        saveUpdate()                     // Configura el botón para guardar la actualización
    }

    /**
     * Configura el listener de fecha de vencimiento para abrir el fragmento de selección de fecha.
     */
    private fun setupFechaVencimientoListener() {
        binding.etFechaVencimiento.setOnClickListener {
            // Crea el fragmento para seleccionar fecha y actualiza la variable newFechaVencimiento
            val dateFragment = EditarProductoFragment { day, month, year ->
                val calendar = Calendar.getInstance().apply {
                    set(year, month, day)
                }
                newFechaVencimiento = calendar.time
                // Actualiza el campo de texto con la fecha seleccionada en formato dd/MM/yyyy
                binding.etFechaVencimiento.setText(
                    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(newFechaVencimiento)
                )
            }
            dateFragment.show(supportFragmentManager, "date_fragment")
        }
    }

    /**
     * Carga los datos del producto desde el ViewModel para ser editados en la pantalla.
     */
    private fun rechargeProducto() {
        productoId?.let { id ->
            viewModel.getProductoById(id).observe(this) { producto ->
                if (producto != null) {
                    Log.d("EditarProductoActivity", "Producto obtenido: ${producto.nombre}")

                    // Asigna directamente los valores actuales del producto a los campos
                    binding.etCodigoProducto.setText(producto.productoId)
                    binding.etCantidadProducto.setText(producto.cantidad.toString())
                    binding.etNombreProducto.setText(producto.nombre)
                    binding.etPrecioUnitario.setText(producto.preciounitario.toString())
                    binding.etCostoUnitario.setText(producto.costounitario.toString())
                    binding.etCategoria.setText(producto.categoria)
                    binding.etFechaVencimiento.setText(
                        producto.fechaVencimiento?.let {
                            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(it)
                        } ?: ""
                    )
                    binding.etProveedor.setText(producto.proveedorId.toString())
                } else {
                    Log.d("EditarProductoActivity", "Producto con ID $productoId no encontrado")
                    Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }

    /**
     * Configura el botón para guardar la actualización del producto.
     */
    private fun saveUpdate() {
        binding.btnActualizarProducto.setOnClickListener {
            if (validarCamposProducto()) { // Primero valida los campos
                updateProducto()          // Luego actualiza el producto
            } else {
                Log.e("EditarProductoActivity", "La validación de campos ha fallado.")
            }
        }
    }

    /**
     * Valida todos los campos del producto antes de intentar actualizarlo.
     * @return true si todos los campos son válidos, de lo contrario false.
     */
    // Valida todos los campos del producto antes de actualizar
    private fun validarCamposProducto(): Boolean {
        val codigo = binding.etCodigoProducto.text.toString()
        val cantidadStr = binding.etCantidadProducto.text.toString()
        val nombre = binding.etNombreProducto.text.toString()
        val precioUnitarioStr = binding.etPrecioUnitario.text.toString()
        val costoUnitarioStr = binding.etCostoUnitario.text.toString()
        val proveedorIdStr = binding.etProveedor.text.toString()
        val categoriaStr = binding.etCategoria.text.toString()

        if (codigo.isBlank() || nombre.isBlank()) {
            Log.e("EditarProductoActivity", "Código y nombre son campos obligatorios.")
            return false
        }
        if (cantidadStr.isBlank() || cantidadStr.toIntOrNull() == null) {
            Log.e("EditarProductoActivity", "La cantidad es inválida o está vacía.")
            return false
        }
        if (precioUnitarioStr.isBlank() || precioUnitarioStr.toDoubleOrNull() == null) {
            Log.e("EditarProductoActivity", "El precio unitario es inválido o está vacío.")
            return false
        }
        if (costoUnitarioStr.isBlank() || costoUnitarioStr.toDoubleOrNull() == null) {
            Log.e("EditarProductoActivity", "El costo unitario es inválido o está vacío.")
            return false
        }
        if (proveedorIdStr.isBlank() || proveedorIdStr.toIntOrNull() == null) {
            Log.e("EditarProductoActivity", "El ID del proveedor es inválido o está vacío.")
            return false
        }
        if (categoriaStr.isBlank()) {
            Log.e("EditarProductoActivity", "La categoria es invalida o esta vacia.")
            return false
        }
        return true
    }

    /**
     * Actualiza el producto con los datos ingresados por el usuario y llama al ViewModel.
     */
    private fun updateProducto() {
        val codigo = binding.etCodigoProducto.text.toString()
        val cantidad = binding.etCantidadProducto.text.toString().toIntOrNull() ?: 0
        val nombre = binding.etNombreProducto.text.toString()
        val precioUnitario = binding.etPrecioUnitario.text.toString().toDoubleOrNull() ?: 0.0
        val costoUnitario = binding.etCostoUnitario.text.toString().toDoubleOrNull() ?: 0.0
        val proveedorId = binding.etProveedor.text.toString().toIntOrNull() ?: 0
        val descripcion = ""
        val categoria = binding.etCategoria.text.toString()

        // Crea un nuevo objeto Producto con los datos actualizados
        val productoActualizado = Producto(
            productoId = codigo,
            nombre = nombre,
            descripcion = descripcion,
            preciounitario = precioUnitario,
            costounitario = costoUnitario,
            cantidad = cantidad,
            categoria = categoria,
            fechaVencimiento = newFechaVencimiento,
            proveedorId = proveedorId,
            fechaIngreso = Date()
        )

        // Llama a la función de actualización en el ViewModel
        viewModel.actualizarProducto(productoActualizado)
        Log.d("EditarProductoActivity", "Producto actualizado con éxito: $productoActualizado")
        finish() // Finaliza la actividad
    }
}
