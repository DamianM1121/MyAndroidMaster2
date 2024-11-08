package com.aristidevs.androidmaster.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.aristidevs.androidmaster.R
import com.aristidevs.androidmaster.firstapp.Database.Entity.NameEntity
import com.aristidevs.androidmaster.firstapp.Database.NameDao.NameDao
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject


@AndroidEntryPoint
class FirstAppActivity : AppCompatActivity() {

    @Inject
    lateinit var nameDao: NameDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_app)

        val btnStart = findViewById<AppCompatButton>(R.id.btnStart)
        val etName = findViewById<AppCompatEditText>(R.id.etName)

        btnStart.setOnClickListener {
            val name = etName.text.toString()

            if (name.isNotEmpty()) {
                saveNameToDatabase(name)  // Guarda en la base de datos

                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("EXTRA_NAME", name)
                startActivity(intent)
            }
        }
    }

    // Guarda el nombre en la base de datos con la fecha y hora actuales
    private fun saveNameToDatabase(name: String) {
        val nameEntity = NameEntity(
            name = name,
            timestamp = Date()  // Se usa Date() para la fecha y hora actuales
        )

        CoroutineScope(Dispatchers.IO).launch {
            nameDao.insertName(nameEntity)
        }
    }
}
