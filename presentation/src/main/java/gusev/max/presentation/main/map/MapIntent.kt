package gusev.max.presentation.main.map

import gusev.max.domain.model.map.LatLngBoundsModel
import gusev.max.domain.model.map.LatLngModel
import gusev.max.presentation.base.BaseIntent

/**
 * Created by v on 19/06/2018.
 */
sealed class MapIntent : BaseIntent {

    object InitialIntent : MapIntent()

    class BrowseEvents(val actionId: Long, val visibleScreenBounds: LatLngBoundsModel) : MapIntent()

    object BrowseActions : MapIntent()

    class NewMarker(val latLng: LatLngModel) : MapIntent()

    class CreateNewEvent(
        val actionId: Long,
        val name: String,
        val description: String,
        val latLng: LatLngModel
    ) : MapIntent()

    class TryLoadEventsAgain(val visibleScreenBounds: LatLngBoundsModel) : MapIntent()

    object TryLoadActionAgain : MapIntent()

}