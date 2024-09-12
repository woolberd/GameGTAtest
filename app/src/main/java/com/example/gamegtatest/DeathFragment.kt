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

        binding.buttonWait?.setOnClickListener {
            binding.buttonWait?.isEnabled = true
        }

        binding.buttonLoseConsciousness?.setOnClickListener {
            binding.buttonLoseConsciousness?.isEnabled = false
        }

        setOutlinedText(binding.killerLevel, "ур. 32", 5f, Color.BLACK, Color.WHITE)
        setOutlinedText(binding.killerName, "Yan Magamedovich", 5f, Color.BLACK, Color.WHITE)

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
        object : CountDownTimer(timeInMillis, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                // Обновляем текст таймера каждую секунду
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                binding.timerText?.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                // Действие, когда таймер закончится
                binding.timerText?.text = "00:00"
            }

        }.start()
    }

    private fun setOutlinedText(view: TextView?, text: String, outlineWidth: Float, outlineColor: Int, fillColor: Int) {
        view?.let {
            val spannableString = SpannableString(text)
            spannableString.setSpan(
                OutlineSpan(outlineWidth, outlineColor, fillColor),
                0, text.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            it.text = spannableString
            it.invalidate()
        } ?: run {
            Log.e("DeathFragment", "${view?.id} is null")
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}