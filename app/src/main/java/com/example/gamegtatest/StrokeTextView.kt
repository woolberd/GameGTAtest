package com.example.gamegtatest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet

class StrokeTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr) {

    override fun onDraw(canvas: Canvas) {
        val textPaint = paint

        val originalColor = currentTextColor

        textPaint.style = Paint.Style.STROKE
        textPaint.strokeWidth = 8f
        textPaint.color = Color.BLACK
        textPaint.isAntiAlias = true

        val text = text.toString()
        val xPos = paddingLeft.toFloat()
        val yPos = baseline.toFloat()
        canvas.drawText(text, xPos, yPos, textPaint)

        textPaint.style = Paint.Style.FILL
        textPaint.color = originalColor

        canvas.drawText(text, xPos, yPos, textPaint)
    }
}
