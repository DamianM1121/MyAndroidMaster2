package ProyectoFinal.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aristidevs.androidmaster.R
import com.aristidevs.androidmaster.databinding.ActivityVentasBinding

class VentasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVentasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVentasBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}