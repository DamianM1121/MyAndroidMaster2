package ProyectoFinal.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ProyectoFinal.data.database.entities.Producto
import com.aristidevs.androidmaster.databinding.ItemNewProductoBinding
import androidx.recyclerview.widget.DiffUtil

class ProductoAdapter(
    private val listener: OnProductoClickListener
) : ListAdapter<Producto, ProductoAdapter.ProductoViewHolder>(ProductoDiffCallback()) {

    interface OnProductoClickListener {
        fun onProductoClick(producto: Producto)
    }

    inner class ProductoViewHolder(private val binding: ItemNewProductoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(producto: Producto) {
            binding.tvNombreProductoNuevo.text = producto.nombre
            binding.tvCantidadProductoNuevo.text = "Cantidad: ${producto.cantidad}"
            binding.tvPrecioUnitarioProducto.text = "Precio: ${producto.preciounitario}"

            // Configuración del clic en el itemView para llamar al listener
            itemView.setOnClickListener {
                listener.onProductoClick(producto)
            }

            // Aquí puedes configurar el ImageView si es necesario
            // binding.imageView.setImageResource(R.drawable.ic_insert_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val binding = ItemNewProductoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ProductoDiffCallback : DiffUtil.ItemCallback<Producto>() {
    override fun areItemsTheSame(oldItem: Producto, newItem: Producto): Boolean {
        return oldItem.productoId == newItem.productoId // Suponiendo que "codigo" es un identificador único
    }

    override fun areContentsTheSame(oldItem: Producto, newItem: Producto): Boolean {
        return oldItem == newItem
    }
}
