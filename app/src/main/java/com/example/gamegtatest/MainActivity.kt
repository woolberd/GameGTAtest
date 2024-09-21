package com.example.gamegtatest

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gamegtatest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var mainBinding: ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)


        if (savedInstanceState == null) {
            val myFragment = MilitaryIDFragment()

            supportFragmentManager.beginTransaction()
                .replace(R.id.main, myFragment)
                .commit()
        }
    }
}