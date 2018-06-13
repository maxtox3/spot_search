package gusev.max.spotsearch.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import gusev.max.spotsearch.R


/**
 * Created by v on 11/06/2018.
 */

class InputLayoutTextWatcherErrorFixer(
    private val inputLayout: TextInputLayout
) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence, st: Int, co: Int, af: Int) {}
    override fun onTextChanged(s: CharSequence, st: Int, be: Int, co: Int) {}

    override fun afterTextChanged(s: Editable) {
        inputLayout.error = null
        inputLayout.isErrorEnabled = false
    }
}

internal fun transformStringColorToInt(color: String): Int {
    return Color.parseColor(color)
}

internal fun makeGradientBackGroundCardView(colorFrom: String, colorTo: String): Drawable {

    val gd = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(transformStringColorToInt(colorFrom), transformStringColorToInt(colorTo))
    )
    gd.cornerRadius = 0f
    return gd
}

internal fun getImageByActionName(context: Context, name: String): Drawable {
    return when (name) {
        Constants.FOOD -> context.getDrawable(R.drawable.food)
        Constants.CHILL_OUT -> context.getDrawable(R.drawable.chill)
        Constants.PLAY -> context.getDrawable(R.drawable.play)
        Constants.RIDE -> context.getDrawable(R.drawable.ride)
        Constants.WATCH -> context.getDrawable(R.drawable.watch)
        Constants.LISTEN -> context.getDrawable(R.drawable.listen)
        Constants.DANCE -> context.getDrawable(R.drawable.dance)
        else -> context.getDrawable(R.drawable.default_img)
    }
}
