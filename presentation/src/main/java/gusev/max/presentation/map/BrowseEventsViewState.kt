package gusev.max.presentation.map

import gusev.max.presentation.base.BaseViewState
import gusev.max.presentation.model.EventModel

/**
 * Created by v on 09/06/2018.
 */
sealed class BrowseEventsViewState(
    val inProgress: Boolean = false,
    val events: List<EventModel>? = null
) : BaseViewState {

    object InProgress : BrowseEventsViewState(true, null)

    object Failed : BrowseEventsViewState(false, null)

    class BrowseEventsSuccess(eventList: List<EventModel>?) :
            BrowseEventsViewState(false, eventList)

    object Idle : BrowseEventsViewState(false, null)
}