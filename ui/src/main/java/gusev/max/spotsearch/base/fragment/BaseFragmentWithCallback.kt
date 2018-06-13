package gusev.max.spotsearch.base.fragment

import android.content.Context
import gusev.max.spotsearch.base.activity.BaseActivityCallback

/**
 * Created by v on 13/06/2018.
 */
abstract class BaseFragmentWithCallback<ACTIVITY_CALLBACK : BaseActivityCallback> : BaseFragment() {

    protected lateinit var activityCallback: ACTIVITY_CALLBACK

    override fun onAttach(context: Context?) {
        try {
            initActivityCallback(context)
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement activityCallback")
        }
        super.onAttach(context)
    }

    abstract fun initActivityCallback(context: Context?)


}