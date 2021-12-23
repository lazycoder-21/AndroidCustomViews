package lostankit7.droid.helper

import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RoundRectShape
import android.util.TypedValue
import java.util.*

fun Resources.deviceIndependentValue(value: Float) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    value, this.displayMetrics
)

fun Resources.deviceIndependentValue(value: Int) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    value.toFloat(), this.displayMetrics
)

fun Int.colorStateList() = ColorStateList.valueOf(this)

fun Int.getRippleMask(radius: Float): Drawable {
    val radiusArray = FloatArray(8)
    Arrays.fill(radiusArray, radius)
    val shape = RoundRectShape(radiusArray, null, null)
    val shapeDrawable = ShapeDrawable(shape)
    shapeDrawable.paint.color = this
    return shapeDrawable
}

fun Int.getCircularRippleMask(): Drawable {
    val shape = OvalShape()
    val shapeDrawable = ShapeDrawable(shape)
    shapeDrawable.paint.color = this
    return shapeDrawable
}



