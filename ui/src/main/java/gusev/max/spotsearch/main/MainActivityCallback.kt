package gusev.max.spotsearch.main

import gusev.max.spotsearch.base.activity.BaseActivityCallback
import gusev.max.spotsearch.model.EventUIModel

/**
 * Created by v on 13/06/2018.
 */
interface MainActivityCallback : BaseActivityCallback {

    fun navigateToMap()

    fun navigateToActions()

    fun navigateToEventFullInfo(event: EventUIModel)

    fun onBackPressed()
}