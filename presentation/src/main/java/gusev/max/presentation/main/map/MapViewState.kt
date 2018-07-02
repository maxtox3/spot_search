package gusev.max.presentation.main.map

import gusev.max.domain.model.map.LatLngModel
import gusev.max.presentation.base.BaseViewState
import gusev.max.presentation.model.ActionModel
import gusev.max.presentation.model.EventModel

/**
 * Created by v on 19/06/2018.
 */
sealed class MapViewState(
    val events: List<EventModel>? = null,
    val actions: List<ActionModel>? = null
) : BaseViewState {

    object InProgress : MapViewState()

    object CreateInProgress : MapViewState()

    object LoadActionsFailed : MapViewState()

    object LoadEventsFailed : MapViewState()

    object CreateEventFailed : MapViewState()

    class ActionsLoaded(val actionList: List<ActionModel>) : MapViewState(actions = actionList)

    class EventsLoaded(val eventList: List<EventModel>) : MapViewState(events = eventList)

    class CreateEventDialogState(val latLngModel: LatLngModel) : MapViewState()

    object CreateEventSuccess : MapViewState()

    object Idle : MapViewState()
}