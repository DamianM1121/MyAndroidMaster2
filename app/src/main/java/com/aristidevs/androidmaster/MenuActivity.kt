package com.aristidevs.androidmaster

import ProyectoFinal.ui.activities.DashboardActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


import com.aristidevs.androidmaster.settings.SettingsActivity



class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnSettings = findViewById<Button>(R.id.btnSettings)
        val btnProyectoFinal = findViewById<Button>(R.id.btnProyectoFinal)


        btnSettings.setOnClickListener { navigateToSettings() }
        btnProyectoFinal.setOnClickListener { navigateToProyectoFinalApp() }
    }

    private fun navigateToSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }


    private fun navigateToProyectoFinalApp(){
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }
}