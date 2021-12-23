package lostankit7.droid

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import lostankit7.droid.helper.setCustomBackground
import lostankit7.droid.helper.update
import lostankit7.droid.helper.updateIcon

class SelectorTextView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {

    lateinit var leftIcon: CustomTextView
    lateinit var tvText: TextView
    lateinit var rightIcon: CustomTextView

    init {
        isClickable = true
        inflate(context, R.layout.layout_selector_textview, this)
        initViews()
        attrs?.manageAttributes()
    }

    private fun AttributeSet.manageAttributes() {
        val typedArray = context.obtainStyledAttributes(this, R.styleable.SelectorTextView)
        try {
            typedArray.apply {
                //managing text in center
                val text = getString(R.styleable.SelectorTextView_text)
                val textColor = getColor(R.styleable.SelectorTextView_text_color, defColor)
                val textSize = getDimension(R.styleable.SelectorTextView_text_dimen, defTextSize)
                this@SelectorTextView.tvText.update(text, textColor, textSize)

                //managing left icon
                val leftIcon = getString(R.styleable.SelectorTextView_left_icon)
                val leftIconColor =
                    getColor(R.styleable.SelectorTextView_left_icon_color, textColor)
                val leftIconSize =
                    getDimension(R.styleable.SelectorTextView_left_icon_dimen, defIconSize)
                val leftIconIsSolid =
                    getBoolean(R.styleable.SelectorTextView_left_icon_is_solid, true)
                this@SelectorTextView.leftIcon.updateIcon(leftIcon, leftIconColor, leftIconSize, leftIconIsSolid)

                //managing right icon
                val rightIcon = getString(R.styleable.SelectorTextView_right_icon)
                val rightIconColor =
                    getColor(R.styleable.SelectorTextView_right_icon_color, textColor)
                val rightIconSize =
                    getDimension(R.styleable.SelectorTextView_right_icon_dimen, defIconSize)
                val rightIconIsSolid =
                    getBoolean(R.styleable.SelectorTextView_right_icon_is_solid, true)
                this@SelectorTextView.rightIcon.updateIcon(rightIcon, rightIconColor, rightIconSize, rightIconIsSolid)

                //managing background
                val solidColor =
                    getColor(R.styleable.SelectorTextView_solid_color, defSolidColor)
                val strokeWidth =
                    getDimension(R.styleable.SelectorTextView_stroke_width, defStrokeWidth)
                val strokeColor =
                    getColor(R.styleable.SelectorTextView_stroke_color, defStrokeColor)
                val cornerRadius =
                    getDimension(R.styleable.SelectorTextView_corner_radius, defCornerRadius)
                val rippleColor =
                    getColor(R.styleable.SelectorTextView_ripple_color, defRippleColor)
                setCustomBackground(solidColor, cornerRadius, strokeColor, strokeWidth, rippleColor)
            }
        } finally {
            typedArray.recycle()
        }
    }

    private fun initViews() {
        leftIcon = findViewById(R.id.left_icon_selector_textview)
        rightIcon = findViewById(R.id.right_icon_selector_textview)
        tvText = findViewById(R.id.text_selector_textview)
    }

    companion object {
        private const val defColor = Color.DKGRAY

        private const val defTextSize = 14f
        private const val defIconSize = 17f

        private const val defSolidColor = Color.WHITE
        private const val defStrokeWidth = 0f
        private const val defStrokeColor = Color.DKGRAY
        private const val defCornerRadius = 15f
        private const val defRippleColor = Color.LTGRAY
    }
}