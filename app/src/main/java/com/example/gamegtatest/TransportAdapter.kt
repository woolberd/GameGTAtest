package com.example.gamegtatest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gamegtatest.databinding.ItemTransportBinding

class TransportAdapter(
    private val onItemSelected: (TransportModel?) -> Unit
) : ListAdapter<TransportModel, TransportAdapter.ViewHolder>(diffUtil) {

    private var selectedItem: TransportModel? = null

    inner class ViewHolder(private val binding: ItemTransportBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun onBind(dataItem: TransportModel?, isSelected: Boolean) {
            dataItem?.let { model ->
                val transportType = TransportType.values().find { it.value == model.id }

                when (transportType) {
                    TransportType.KZ -> binding.numDigitsKz?.text = "777xxx"
                    TransportType.BY -> binding.numDigitsBy?.text ="6351 MI-3"
                    TransportType.RUS -> binding.numDigitsRus?.text ="P 070 BK"
                    TransportType.AM -> binding.numDigitsAm?.text = "34 VG 885"
                    TransportType.UA -> binding.numDigitsUa?.text ="AK 1234 B"
                    else -> "Неизвестно"
                }

                when (transportType) {
                    TransportType.KZ -> binding.kzNumDigitsConstraint?.visibility = View.VISIBLE
                    TransportType.BY -> binding.byNumDigitsConstraint?.visibility = View.VISIBLE
                    TransportType.RUS -> binding.rusNumDigitsConstraint?.visibility = View.VISIBLE
                    TransportType.AM -> binding.amNumDigitsConstraint?.visibility = View.VISIBLE
                    TransportType.UA -> binding.uaNumDigitsConstraint?.visibility = View.VISIBLE
                    else -> binding.carNumberDetails?.visibility = View.GONE
                }

                if (isSelected) {
                    binding.root.setBackgroundResource(R.drawable.item_car_selected_corners)
                } else {
                    binding.root.setBackgroundResource(R.drawable.item_car_corners)
                }


                binding.root.setOnClickListener {
                    onItemSelected(dataItem)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTransportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item, item == selectedItem)
    }

    fun updateSelectedItem(newSelectedItem: TransportModel?) {
        val previousItem = selectedItem
        selectedItem = newSelectedItem
        notifyItemChanged(currentList.indexOf(previousItem))
        notifyItemChanged(currentList.indexOf(newSelectedItem))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<TransportModel>() {
            override fun areItemsTheSame(
                oldItem: TransportModel,
                newItem: TransportModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TransportModel,
                newItem: TransportModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
