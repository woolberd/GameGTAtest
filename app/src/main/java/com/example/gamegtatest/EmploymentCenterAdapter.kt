package com.example.gamegtatest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gamegtatest.databinding.ItemEmploymentCenterBinding

class EmploymentCenterAdapter :
    ListAdapter<EmploymentCenterModel, EmploymentCenterAdapter.ViewHolder>(diffUtil) {

    class ViewHolder(private val binding: ItemEmploymentCenterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(dataItem: EmploymentCenterModel?) {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemEmploymentCenterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<EmploymentCenterModel>() {
            override fun areItemsTheSame(
                oldItem: EmploymentCenterModel,
                newItem: EmploymentCenterModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: EmploymentCenterModel,
                newItem: EmploymentCenterModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
