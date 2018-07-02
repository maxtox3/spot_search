package gusev.max.spotsearch.main.event

import gusev.max.spotsearch.model.EventUIModel

/**
 * Created by v on 23/06/2018.
 */
interface CreateEventCallback {

    fun createEvent(eventUIModel: EventUIModel)
}