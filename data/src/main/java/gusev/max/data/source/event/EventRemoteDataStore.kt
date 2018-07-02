package gusev.max.data.source.event

import gusev.max.data.entity.EventEntity
import gusev.max.data.source.base.BaseRemoteDataStore
import gusev.max.domain.model.map.LatLngBoundsModel
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by v on 10/06/2018.
 */
class EventRemoteDataStore @Inject constructor(
    private val remoteStore: EventRemote
) : BaseRemoteDataStore<EventEntity>(remoteStore), EventDataStore {

    override fun getEventsByBounds(bounds: LatLngBoundsModel): Flowable<List<EventEntity>> {
        return remoteStore.getEventsByBounds(bounds)
    }

    override fun getEventsByBoundsAndActionId(
        bounds: LatLngBoundsModel,
        actionId: Long
    ): Flowable<List<EventEntity>> {
        return remoteStore.getEventsByBoundsAndActionId(bounds, actionId)
    }
}