package com.example.gamegtatest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gamegtatest.databinding.ItemNumAmBinding
import com.example.gamegtatest.databinding.ItemNumAzBinding
import com.example.gamegtatest.databinding.ItemNumByBinding
import com.example.gamegtatest.databinding.ItemNumGeBinding
import com.example.gamegtatest.databinding.ItemNumKzBinding
import com.example.gamegtatest.databinding.ItemNumRusBinding
import com.example.gamegtatest.databinding.ItemNumUaBinding
import com.example.gamegtatest.databinding.ItemTransportBinding

class TransportAdapter(
    private val onItemSelected: (TransportModel?) -> Unit
) : ListAdapter<TransportModel, TransportAdapter.ViewHolder>(diffUtil) {

    private var selectedItem: TransportModel? = null

    inner class ViewHolder(private val binding: ItemTransportBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun onBind(dataItem: TransportModel?, isSelected: Boolean) {
            dataItem?.let { model ->
                binding.frameLayout?.removeAllViews()

                val transportType = TransportType.values().find { it.value == model.id }

                val numberLayoutResId = when (transportType) {
                    TransportType.KZ -> R.layout.item_num_kz
                    TransportType.BY -> R.layout.item_num_by
                    TransportType.RUS -> R.layout.item_num_rus
                    TransportType.AM -> R.layout.item_num_am
                    TransportType.UA -> R.layout.item_num_ua
                    TransportType.GE -> R.layout.item_num_ge
                    TransportType.AZ -> R.layout.item_num_az
                    else -> null
                }

                numberLayoutResId?.let { layoutResId ->
                    val inflater = LayoutInflater.from(binding.root.context)
                    val view = when (transportType) {
                        TransportType.KZ -> {
                            val kzBinding =
                                ItemNumKzBinding.inflate(inflater, binding.frameLayout, false)
                            kzBinding.numDigitsKz.text = "777XXX"
                            kzBinding.root
                        }

                        TransportType.BY -> {
                            val byBinding =
                                ItemNumByBinding.inflate(inflater, binding.frameLayout, false)
                            byBinding.numDigitsBy.text = "6351 MI-3"
                            byBinding.root
                        }

                        TransportType.RUS -> {
                            val rusBinding =
                                ItemNumRusBinding.inflate(inflater, binding.frameLayout, false)
                            rusBinding.numDigitsRus.text = "P 070 BK"
                            rusBinding.root
                        }

                        TransportType.AM -> {
                            val amBinding =
                                ItemNumAmBinding.inflate(inflater, binding.frameLayout, false)
                            amBinding.numDigitsAm.text = "34 VG 885"
                            amBinding.root
                        }

                        TransportType.UA -> {
                            val uaBinding =
                                ItemNumUaBinding.inflate(inflater, binding.frameLayout, false)
                            uaBinding.numDigitsUa.text = "AK 1234 AB"
                            uaBinding.root
                        }

                        TransportType.GE -> {
                            val geBinding =
                                ItemNumGeBinding.inflate(inflater, binding.frameLayout, false)
                            geBinding.numDigitsGe.text = "ZZ-856-PP"
                            geBinding.root
                        }

                        TransportType.AZ -> {
                            val azBinding =
                                ItemNumAzBinding.inflate(inflater, binding.frameLayout, false)
                            azBinding.numDigitsAz.text = "10-BC-123"
                            azBinding.root
                        }

                        else -> null
                    }

                    view?.let { binding.frameLayout?.addView(it) }
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
