package gusev.max.presentation.main.map

import gusev.max.domain.model.map.LatLngBoundsModel
import gusev.max.presentation.base.BaseAction

/**
 * Created by v on 09/06/2018.
 */
sealed class BrowseEventsAction : BaseAction {

    class BrowseEvents(val bounds: LatLngBoundsModel?) : BrowseEventsAction()
}