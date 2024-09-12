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
import androidx.core.view.doOnPreDraw
import androidx.core.view.updateLayoutParams
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
            binding.timerContainer?.updateLayoutParams<ConstraintLayout.LayoutParams> {
                width = cacheWidth!!.toInt()
            }
        }
        val margin1 =  (binding.timerLogo!!.x - binding.timerContainer!!.x).toInt()

        binding.timerText?.doOnPreDraw {
            val cacheWidth = binding.timerText?.width
            binding.timerText?.updateLayoutParams<ConstraintLayout.LayoutParams> {
                startToEnd = ConstraintLayout.LayoutParams.UNSET
                marginStart = (binding.timerLogo!!.x - binding.timerContainer!!.x).toInt()
            }
        }
        binding.timerLogo?.doOnPreDraw {
            val cacheWidth = binding.timerLogo?.width
            binding.timerLogo?.updateLayoutParams<ConstraintLayout.LayoutParams> {
                endToStart = ConstraintLayout.LayoutParams.UNSET
                marginStart = (binding.timerLogo!!.x - binding.timerContainer!!.x).toInt()
            }
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