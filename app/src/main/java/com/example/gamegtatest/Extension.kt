package com.example.gamegtatest

import android.content.Context
import android.content.res.Configuration
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.google.android.material.snackbar.Snackbar


fun Int.dp(context: Context): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    context.resources.displayMetrics).toInt()

fun createMaterialDividerItemDecoration(context: Context, color: Int, dp: Int, orientation: Int, isLast: Boolean): MaterialDividerItemDecoration {
    return MaterialDividerItemDecoration(context, orientation).apply {
        isLastItemDecorated = isLast
        dividerColor = color
        dividerThickness = dp
    }
}

fun Context.checkScreenSizeAndSetDecoration(screen: String, view: View, recyclerView: RecyclerView) {
    val screenSize = resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK

    val itemDecoration = when (screenSize) {
        Configuration.SCREENLAYOUT_SIZE_NORMAL -> {
            Snackbar.make(view, "$screen: ЗАПУСТИЛСЯ NORMAL ЭКРАН", Snackbar.LENGTH_LONG).show()
            createMaterialDividerItemDecoration(
                this,
                android.R.color.transparent,
                5.dp(this),  // стандартный отступ
                RecyclerView.HORIZONTAL,
                true
            )
        }
        Configuration.SCREENLAYOUT_SIZE_LARGE -> {
            Snackbar.make(view, "$screen: ЗАПУСТИЛСЯ LARGE ЭКРАН", Snackbar.LENGTH_LONG).show()
            createMaterialDividerItemDecoration(
                this,
                android.R.color.transparent,
                10.dp(this),  // больше отступ для больших экранов
                RecyclerView.HORIZONTAL,
                true
            )
        }
        Configuration.SCREENLAYOUT_SIZE_XLARGE -> {
            Snackbar.make(view, "$screen: ЗАПУСТИЛСЯ XLARGE ЭКРАН", Snackbar.LENGTH_LONG).show()
            createMaterialDividerItemDecoration(
                this,
                android.R.color.transparent,
                15.dp(this),  // самый большой отступ для экранов XL
                RecyclerView.HORIZONTAL,
                true
            )
        }
        else -> {
            Snackbar.make(view, "$screen: ЗАПУСТИЛСЯ НЕИЗВЕСТНЫЙ ЭКРАН", Snackbar.LENGTH_LONG).show()
            createMaterialDividerItemDecoration(
                this,
                android.R.color.transparent,
                5.dp(this),  // стандартный отступ для неизвестных экранов
                RecyclerView.HORIZONTAL,
                true
            )
        }
    }

    recyclerView.addItemDecoration(itemDecoration)
}
