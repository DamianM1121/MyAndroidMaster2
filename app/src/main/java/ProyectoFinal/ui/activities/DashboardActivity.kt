package ProyectoFinal.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aristidevs.androidmaster.R
import com.aristidevs.androidmaster.databinding.ActivityDashboardBinding


class DashboardActivity : AppCompatActivity() {

    //    Se habilita el binding
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()

    }

    private fun initListener() {
        navigateToDashboard()
        navigateToInventario()
        navigateToDeudas()
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
}
