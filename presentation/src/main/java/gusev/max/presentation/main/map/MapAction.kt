package gusev.max.presentation.main.map

import gusev.max.domain.model.Event
import gusev.max.domain.model.map.LatLngBoundsModel
import gusev.max.domain.model.map.LatLngModel
import gusev.max.presentation.base.BaseAction

/**
 * Created by v on 19/06/2018.
 */
sealed class MapAction : BaseAction {

    class BrowseEvents(val bounds: LatLngBoundsModel?, val actionId: Long = -1) : MapAction()

    object BrowseActions : MapAction()

    class AddNewMarker(val latLng: LatLngModel) : MapAction()

    class CreateNewEvent(val event: Event) : MapAction()

}