package gusev.max.spotsearch.utils

import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher

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
