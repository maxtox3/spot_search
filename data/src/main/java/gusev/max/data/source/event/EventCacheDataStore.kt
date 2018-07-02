package gusev.max.data.source.event

import gusev.max.data.entity.EventEntity
import gusev.max.data.source.base.BaseCacheDataStore
import gusev.max.domain.model.map.LatLngBoundsModel
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by v on 10/06/2018.
 */
class EventCacheDataStore @Inject constructor(
    private val cacheStore: EventCache
) : BaseCacheDataStore<EventEntity>(cacheStore), EventDataStore {

    override fun getEventsByBounds(bounds: LatLngBoundsModel): Flowable<List<EventEntity>> {
        return cacheStore.getEventsByBounds(bounds)
    }

    override fun getEventsByBoundsAndActionId(
        bounds: LatLngBoundsModel,
        actionId: Long
    ): Flowable<List<EventEntity>> {
        return cacheStore.getEventsByBoundsAndActionId(bounds, actionId)
    }
}