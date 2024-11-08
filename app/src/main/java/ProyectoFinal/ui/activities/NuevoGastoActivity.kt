package ProyectoFinal.ui.activities

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aristidevs.androidmaster.R
import com.aristidevs.androidmaster.databinding.ActivityNuevoGastoBinding

class NuevoGastoActivity : AppCompatActivity() {

    private lateinit var binding:ActivityNuevoGastoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNuevoGastoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}