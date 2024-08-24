package com.example.gamegtatest

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gamegtatest.databinding.ActivityMainBinding
import com.example.gamegtatest.databinding.ApartmentInfoBinding
import com.example.gamegtatest.databinding.ApartmentManagementBinding
import com.example.gamegtatest.databinding.HouseManagementBinding
import com.example.gamegtatest.databinding.MenuApartmentBinding

class MainActivity : AppCompatActivity() {

    private var binding: HouseManagementBinding? = null
   // private lateinit var notificationState: NotificationState


    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var houseManagementBinding: ApartmentInfoBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        binding = HouseManagementBinding.inflate(layoutInflater)
//        setContentView(binding?.root)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        houseManagementBinding = ApartmentInfoBinding.inflate(layoutInflater)

        val frameLayout: FrameLayout = mainBinding.frameLayout
        frameLayout.addView(houseManagementBinding.root)

//        notificationState = NotificationState(this, binding!!.root)
//        notificationState.onCreateView()
    }
}