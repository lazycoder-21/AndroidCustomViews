package lostankit7.droid

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import lostankit7.droid.helper.setGradientBackground

/**
 * Custom Button which provides extended functionality of Button
 * start and end color for gradient color
 * stroke width, color , and can show ripple effect
 */
class CustomButton @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    AppCompatButton(context, attrs, defStyleAttr) {

    init {
        isClickable = true
        gravity = Gravity.CENTER
        isAllCaps = true
        attrs?.manageAttributes()
    }

    private fun AttributeSet.manageAttributes() {
        val typedArray = context.obtainStyledAttributes(this, R.styleable.CustomButton)
        try {
            typedArray.apply {
                val startColor = getColor(R.styleable.CustomButton_start_color, defStartColor)
                val endColor = getColor(R.styleable.CustomButton_end_color, defEndColor)

                val cornerRadius =
                    getDimension(R.styleable.CustomButton_corner_radius, defCornerRadius)
                val strokeWidth =
                    getDimension(R.styleable.CustomButton_stroke_width, defStrokeWidth)
                val strokeColor = getColor(R.styleable.CustomButton_stroke_color, defStrokeColor)

                val rippleColor =
                    getColor(R.styleable.CustomButton_ripple_color, defRippleColor)

                setGradientBackground(
                    startColor,
                    endColor,
                    GradientDrawable.Orientation.LEFT_RIGHT,
                    cornerRadius,
                    strokeColor,
                    strokeWidth,
                    rippleColor
                )
            }
        } finally {
            typedArray.recycle()
        }
    }

    companion object {
        private const val defHeight = 40
        private const val defStartColor = Color.WHITE
        private const val defEndColor = Color.WHITE

        private const val defCornerRadius = 10f
        private const val defStrokeWidth = 5f
        private const val defStrokeColor = Color.TRANSPARENT

        private const val defRippleColor = Color.LTGRAY

    }
}
