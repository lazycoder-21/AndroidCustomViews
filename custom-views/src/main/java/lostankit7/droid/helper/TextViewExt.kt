package lostankit7.droid.helper

import android.widget.TextView
import lostankit7.droid.CustomTextView

fun CustomTextView.updateIcon(
    icon: String?,
    iconColor: Int,
    iconSize: Float,
    iconIsSolid: Boolean
) {
    icon?.let {
        show()
        this.text = icon
        this.setTextColor(iconColor)
        this.textSize = iconSize
        if (iconIsSolid) isSolidIcon() else isRegularIcon()
    }
}

fun TextView.update(text: String?, textColor: Int, textSize: Float) {
    text?.let {
        show()
        this.text = text
        this.setTextColor(textColor)
        this.textSize = textSize
    }
}