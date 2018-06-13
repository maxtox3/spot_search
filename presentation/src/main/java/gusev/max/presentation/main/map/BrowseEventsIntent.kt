package gusev.max.presentation.main.map

import gusev.max.domain.model.map.LatLngBoundsModel
import gusev.max.presentation.base.BaseIntent

/**
 * Created by v on 09/06/2018.
 */
sealed class BrowseEventsIntent : BaseIntent {

    object InitialIntent : BrowseEventsIntent()

    class BrowseEvents(val visibleScreenBounds: LatLngBoundsModel) : BrowseEventsIntent()


}