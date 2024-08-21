package com.example.gamegtatest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gamegtatest.databinding.ActivityMainBinding
import com.example.gamegtatest.databinding.MenuApartmentBinding

class MainActivity : AppCompatActivity() {

    private var binding: MenuApartmentBinding? = null
   // private lateinit var notificationState: NotificationState

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = MenuApartmentBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.menu?.let { mainView ->
            ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

//        notificationState = NotificationState(this, binding!!.root)
//        notificationState.onCreateView()
    }
}