package com.example.gamegtatest

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Spannable
import android.text.SpannableString
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.doOnPreDraw
import androidx.core.view.postDelayed
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import com.example.gamegtatest.databinding.FragmentDeathBinding

class DeathFragment : Fragment() {

    private var _binding: FragmentDeathBinding? = null
    private val binding get() = _binding!!
    private var timerDuration: Long = 180000
    private var countdownTimer: CountDownTimer? = null
    private var remainingTime = timerDuration

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeathBinding.inflate(inflater, container, false)
        startCountdownTimer(timerDuration)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.timerContainer?.visibility = View.INVISIBLE
        binding.timerLogo?.visibility = View.INVISIBLE
        binding.timerText?.visibility = View.INVISIBLE

        binding.buttonWait?.setOnClickListener {
            startCountdownTimer(timerDuration)
            binding.buttonWait?.isEnabled = false
            binding.buttonLoseConsciousness?.isEnabled = false
            binding.timerContainer?.visibility = View.VISIBLE
            binding.timerLogo?.visibility = View.VISIBLE
            binding.timerText?.visibility = View.VISIBLE
            binding.tvWaitDoctor?.isEnabled = false
            binding.tvLoseConsciousness?.isEnabled = false
        }

        binding.buttonLoseConsciousness?.setOnClickListener {
            binding.buttonLoseConsciousness?.isEnabled = false
            binding.tvLoseConsciousness?.isEnabled = false
        }

        binding.timerContainer?.doOnPreDraw {
            val cacheWidth = binding.timerContainer?.width
            val cacheHeight = binding.timerContainer?.height

            val x3 = binding.timerContainer!!.x
            val y3 = binding.timerContainer!!.y

            val params3 = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                width = cacheWidth!!
                height = cacheHeight!!
                topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            }

            binding.timerContainer!!.layoutParams = params3
            binding.timerContainer!!.translationX = x3
            binding.timerContainer!!.translationY = y3

            val x1 = binding.timerText!!.x
            val y1 = binding.timerText!!.y

            val x2 = binding.timerLogo!!.x
            val y2 = binding.timerLogo!!.y

            val params1 = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            }

            val testWidth = binding.timerLogo!!.width
            val testHeight = binding.timerLogo!!.height

            val params2 = ConstraintLayout.LayoutParams(
                testWidth,
                testHeight
            ).apply {
                topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            }

            binding.timerLogo!!.layoutParams = params2
            binding.timerLogo!!.translationX = x2
            binding.timerLogo!!.translationY = y2

            binding.timerText!!.layoutParams = params1
            binding.timerText!!.translationX = x1
            binding.timerText!!.translationY = y1
        }

    }


    private fun startCountdownTimer(timeInMillis: Long) {
        countdownTimer?.cancel()
        countdownTimer = object : CountDownTimer(timeInMillis, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                remainingTime = millisUntilFinished
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                binding.timerText?.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                remainingTime = 0
                binding.timerText?.text = "00:00"
            }

        }.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        countdownTimer?.cancel()
        _binding = null
    }
}