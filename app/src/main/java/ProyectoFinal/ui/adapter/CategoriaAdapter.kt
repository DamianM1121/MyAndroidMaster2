package ProyectoFinal.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aristidevs.androidmaster.databinding.ItemCategoriesNewBinding

class CategoriaAdapter(
    private val listener: OnCategoryClickListener
) : ListAdapter<String, CategoriaAdapter.CategoriaViewHolder>(CategoriaDiffCallback()) {

    interface OnCategoryClickListener {
        fun onCategoryClick(category: String)
    }

    inner class CategoriaViewHolder(private val binding: ItemCategoriesNewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: String) {
            binding.tvCategoryName.text = category

            // Configuración de clic en el itemView para llamar al listener
            itemView.setOnClickListener {
                listener.onCategoryClick(category)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val binding = ItemCategoriesNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CategoriaDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
}
