package gusev.max.domain.repository

import gusev.max.domain.model.Event
import gusev.max.domain.model.map.LatLngBoundsModel
import io.reactivex.Flowable

/**
 * Created by v on 10/06/2018.
 */
interface EventsRepository : BaseRepository<Event> {

    fun getModelsByBounds(bounds: LatLngBoundsModel): Flowable<List<Event>>
}