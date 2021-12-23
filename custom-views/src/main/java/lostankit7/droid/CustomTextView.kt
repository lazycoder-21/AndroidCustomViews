package lostankit7.droid

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import lostankit7.droid.helper.setCustomBackground
import lostankit7.droid.helper.setCustomCircularBackground
import java.util.*

/**
 * Custom TextView which gives extended functionality to text view
 * @params solid_color  background color of circular view
 * @params stroke_color and stroke_width provides width and color of stroke
 * @params show_ripple, ripple_color and mask_color are attribute for showing ripple effect if view is touched
 */
class CustomTextView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    AppCompatTextView(context, attrs, defStyleAttr) {

    private var mDrawable: GradientDrawable? = null

    init {
        gravity = Gravity.CENTER
        val arr = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView)
        arr.manageAttributes()
        arr.recycle()
    }

    private fun TypedArray.manageAttributes() {

        try {
            val isFaSolid = getBoolean(R.styleable.CustomTextView_fa_solid, false)
            val isFaRegular = getBoolean(R.styleable.CustomTextView_fa_regular, false)
            val isFaBrand = getBoolean(R.styleable.CustomTextView_fa_brand, false)
            updateTypeface(isFaSolid, isFaRegular, isFaBrand)

            val colorSolid = getColor(R.styleable.CustomTextView_solid_color, defSolidColor)

            val isBgCircular =
                getBoolean(R.styleable.CustomTextView_background_circular, defBgCircular)
            val cornerRadius =
                getDimension(R.styleable.CustomTextView_corner_radius, defCornerRadius)
            val strokeWidth = getDimension(R.styleable.CustomTextView_stroke_width, defStrokeWidth)
            val colorStroke = getColor(R.styleable.CustomTextView_stroke_color, defStrokeColor)

            val rippleColor = getColor(R.styleable.CustomTextView_ripple_color, defRippleColor)

            mDrawable = if (isBgCircular)
                setCustomCircularBackground(colorSolid, colorStroke, strokeWidth, rippleColor)
            else
                setCustomBackground(colorSolid, cornerRadius, colorStroke, strokeWidth, rippleColor)

        } catch (e: Exception) {
        }
    }

    fun solidColor(color: Int) = mDrawable?.setColor(color)

    fun isSolidIcon() {
        typeface = FontCache[context, FontCache.faSolidIcon]
        invalidate()
    }

    fun isRegularIcon() {
        typeface = FontCache[context, FontCache.faRegularIcon]
        invalidate()
    }

    private fun updateTypeface(isSolidIcon: Boolean, isRegularIcon: Boolean, isBrandIcon: Boolean) {
        typeface = when {
            isBrandIcon -> FontCache[context, FontCache.faBrandIcon]
            isSolidIcon -> FontCache[context, FontCache.faSolidIcon]
            isRegularIcon -> FontCache[context, FontCache.faRegularIcon]
            else -> typeface
        }
    }

    object FontCache {
        const val faBrandIcon = "fonts/FaBrand5.ttf"
        const val faSolidIcon = "fonts/FaSolid5.ttf"
        const val faRegularIcon = "fonts/FaRegular5.ttf"

        private val fontCache: Hashtable<String, Typeface> = Hashtable()

        operator fun get(context: Context, name: String?): Typeface? {
            var typeface: Typeface? = fontCache[name]
            if (typeface == null) {
                typeface = try {
                    Typeface.createFromAsset(context.assets, name)
                } catch (e: Exception) {
                    return null
                }
                fontCache[name] = typeface
            }
            return typeface
        }
    }

    companion object {
        private const val defSolidColor = Color.TRANSPARENT

        private const val defStrokeColor = Color.LTGRAY
        private const val defStrokeWidth = 0f
        private const val defCornerRadius = 15f
        private const val defBgCircular = false

        private const val defRippleColor = Color.TRANSPARENT
    }
}