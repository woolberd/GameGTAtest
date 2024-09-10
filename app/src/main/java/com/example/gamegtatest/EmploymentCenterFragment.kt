package com.example.gamegtatest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gamegtatest.databinding.FragmentEmploymentCenterBinding

class EmploymentCenterFragment : Fragment() {

    private var _binding: FragmentEmploymentCenterBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterCenter: EmploymentCenterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmploymentCenterBinding.inflate(inflater, container, false)
        val rootView = binding.root
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterCenter = EmploymentCenterAdapter()

        binding.recycler.adapter = adapterCenter

        val exampleData = listOf(
            EmploymentCenterModel(1),
            EmploymentCenterModel(2),
            EmploymentCenterModel(3),
            EmploymentCenterModel(4)
        )
        adapterCenter.submitList(exampleData)
    }

}