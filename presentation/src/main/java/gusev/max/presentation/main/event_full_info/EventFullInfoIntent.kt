package gusev.max.presentation.main.event_full_info

import gusev.max.presentation.base.BaseIntent

/**
 * Created by v on 27/06/2018.
 */
sealed class EventFullInfoIntent : BaseIntent {

    object InitialIntent : EventFullInfoIntent()

    class BrowseEvent(val eventId: Long) : EventFullInfoIntent()

    class BrowseComments(val eventId: Long) : EventFullInfoIntent()
}