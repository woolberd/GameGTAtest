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
import com.example.gamegtatest.databinding.ApartmentInfoBinding
import com.example.gamegtatest.databinding.ApartmentManagementBinding
import com.example.gamegtatest.databinding.HouseManagementBinding
import com.example.gamegtatest.databinding.MenuApartmentBinding
import com.example.gamegtatest.databinding.TransportInfoBinding

class MainActivity : AppCompatActivity() {

   // private lateinit var notificationState: NotificationState
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var adapterTransport: TransportAdapter
    private lateinit var binding: TransportInfoBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)


        binding = TransportInfoBinding.inflate(layoutInflater)
        adapterTransport = TransportAdapter { selectedItem ->
            adapterTransport.updateSelectedItem(selectedItem)
            updateButtonBackgrounds(selectedItem)
        }
        binding.recyclerTransport.adapter = adapterTransport

        val transportListFromBackend = getTransportDataFromBackend()

        adapterTransport.submitList(transportListFromBackend)
        mainBinding.frameLayout.addView(binding.root)
//        val frameLayout: FrameLayout = mainBinding.frameLayout
//        frameLayout.addView(houseManagementBinding.root)

//        notificationState = NotificationState(this, binding!!.root)
//        notificationState.onCreateView()
    }

    private fun getTransportDataFromBackend(): List<TransportModel> {
        val response = listOf(
            TransportModel(id = TransportType.KZ.value),
            TransportModel(id = TransportType.BY.value),
            TransportModel(id = TransportType.RUS.value),
            TransportModel(id = TransportType.AM.value),
            TransportModel(id = TransportType.UA.value)
        )

        return response
    }

    private fun updateButtonBackgrounds(selectedItem: TransportModel?) {
        if (selectedItem != null) {
            binding.btnDeliver.setBackgroundResource(R.drawable.blue_gradient)
            binding.btnInformation.setBackgroundResource(R.drawable.light_gray_gradient)
            binding.btnSell.setBackgroundResource(R.drawable.red_gradient)

            binding.tvSell?.setTextColor(Color.WHITE)
            binding.tvDeliver?.setTextColor(Color.WHITE)
            binding.tvInformation?.setTextColor(Color.WHITE)
        } else {
            binding.btnDeliver.setBackgroundResource(R.drawable.dark_grey_gradient)
            binding.btnInformation.setBackgroundResource(R.drawable.dark_grey_gradient)
            binding.btnSell.setBackgroundResource(R.drawable.dark_grey_gradient)

            binding.tvSell?.setTextColor(Color.GRAY)
            binding.tvDeliver?.setTextColor(Color.GRAY)
            binding.tvInformation?.setTextColor(Color.GRAY)
        }
    }
}