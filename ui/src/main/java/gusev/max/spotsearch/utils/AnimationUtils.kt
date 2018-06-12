package gusev.max.spotsearch.utils

import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo


/**
 * Created by v on 11/06/2018.
 */
object AnimationUtils {

    fun shake(view: View) {
        YoYo.with(Techniques.Shake)
            .duration(300)
            .playOn(view)
    }
}