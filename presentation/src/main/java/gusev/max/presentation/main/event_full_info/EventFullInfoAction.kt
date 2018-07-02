package gusev.max.presentation.main.event_full_info

import gusev.max.presentation.base.BaseAction

/**
 * Created by v on 27/06/2018.
 */
sealed class EventFullInfoAction : BaseAction {

    class BrowseEvent(val eventId: Long?) : EventFullInfoAction()

    class BrowseComments(val eventId: Long) : EventFullInfoAction()

}