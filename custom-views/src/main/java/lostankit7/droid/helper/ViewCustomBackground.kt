package lostankit7.droid.helper

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.view.View

fun View.setCustomBackground(
    solidColor: Int,
    cornerRadius: Float,
    strokeColor: Int,
    strokeWidth: Float,
    rippleColor: Int,
    maskColor: Int = Color.GRAY
): GradientDrawable {
    val drawable = GradientDrawable().also {
        it.cornerRadius = cornerRadius
        it.setColor(solidColor)
        it.setStroke(strokeWidth.toInt(), strokeColor)
    }
    background = RippleDrawable(
        ColorStateList.valueOf(rippleColor),
        drawable,
        maskColor.getRippleMask(cornerRadius)
    )
    return drawable
}

fun View.setGradientBackground(
    startColor: Int,
    endColor: Int,
    gradDirection: GradientDrawable.Orientation,
    cornerRadius: Float,
    strokeColor: Int,
    strokeWidth: Float,
    rippleColor: Int,
    maskColor: Int = Color.GRAY
): GradientDrawable {
    val drawable = GradientDrawable(
        gradDirection,
        intArrayOf(startColor, endColor)
    ).also {
        it.cornerRadius = cornerRadius
        it.setStroke(strokeWidth.toInt(), strokeColor)
    }
    background = RippleDrawable(
        ColorStateList.valueOf(rippleColor),
        drawable,
        maskColor.getRippleMask(cornerRadius)
    )
    return drawable
}

fun View.setCustomCircularBackground(
    solidColor: Int,
    strokeColor: Int,
    strokeWidth: Float,
    rippleColor: Int,
    maskColor: Int = Color.GRAY
): GradientDrawable {
    val drawable = GradientDrawable().also {
        it.shape = GradientDrawable.OVAL
        it.cornerRadii = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
        it.setColor(solidColor)
        it.setStroke(strokeWidth.toInt(), strokeColor)
    }
    background = RippleDrawable(
        rippleColor.colorStateList(),
        drawable,
        maskColor.getCircularRippleMask()
    )
    return drawable
}

