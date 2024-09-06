package com.example.gamegtatest

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gamegtatest.databinding.FragmentMainStatisticsBinding

class MainStatisticsFragment : Fragment() {

    private var _binding: FragmentMainStatisticsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainStatisticsBinding.inflate(inflater, container, false)
        val rootView = binding.root

        loadLayout(R.layout.statistics_info)
        binding.btnStatistics.setBackgroundResource(R.drawable.white_selected_background)

        binding.btnStatistics.setOnClickListener {
            loadLayout(R.layout.statistics_info)
            binding.tvSkills.setTextColor(Color.WHITE)
            binding.tvStatistics.setTextColor(Color.BLACK)
            binding.btnStatistics.setBackgroundResource(R.drawable.white_selected_background)
            binding.btnSkills.setBackgroundResource(R.drawable.grey_not_selected_background)
        }

        binding.btnSkills.setOnClickListener {
            loadLayout(R.layout.skills_info)
            binding.tvSkills.setTextColor(Color.BLACK)
            binding.tvStatistics.setTextColor(Color.WHITE)
            binding.btnSkills.setBackgroundResource(R.drawable.white_selected_background)
            binding.btnStatistics.setBackgroundResource(R.drawable.grey_not_selected_background)
        }

        return rootView
    }

    private fun loadLayout(layoutResId: Int) {
        binding.frameLayout.removeAllViews()
        val inflater = LayoutInflater.from(context)
        val nestedLayout = inflater.inflate(layoutResId, binding.frameLayout, false)
        binding.frameLayout.addView(nestedLayout)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
