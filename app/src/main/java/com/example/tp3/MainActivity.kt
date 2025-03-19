package com.example.tp3

import HomePageFragment
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //Display the HomePage fragment that contains the buttons Nouvelle inscription and Connexion
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomePageFragment()).commit()

    }
}