package com.example.gamegtatest

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextPaint
import android.text.style.CharacterStyle
import android.text.style.ReplacementSpan

class OutlineSpan(
    private val strokeWidth: Float,
    private val strokeColor: Int,
    private val fillColor: Int
) : CharacterStyle() {

    override fun updateDrawState(tp: TextPaint) {
        // Сохраняем изначальные настройки
        val oldColor = tp.color
        val oldStyle = tp.style

        // Настраиваем параметры обводки
        tp.style = Paint.Style.STROKE
        tp.strokeWidth = strokeWidth
        tp.color = strokeColor
        tp.isFakeBoldText = true

        // Рисуем обводку
        tp.bgColor = Color.TRANSPARENT

        // Рисуем поверх заливку
        tp.style = Paint.Style.FILL
        tp.color = fillColor

        // Восстанавливаем изначальные параметры
        tp.strokeWidth = 0f
        tp.color = oldColor
        tp.style = oldStyle
    }
}

