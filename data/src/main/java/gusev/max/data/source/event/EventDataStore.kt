package gusev.max.data.source.event

import gusev.max.data.entity.EventEntity
import gusev.max.data.source.base.BaseDataStore
import gusev.max.domain.model.map.LatLngBoundsModel
import io.reactivex.Flowable

/**
 * Created by v on 10/06/2018.
 */
interface EventDataStore : BaseDataStore<EventEntity> {

    fun getEventsByBounds(bounds: LatLngBoundsModel): Flowable<List<EventEntity>>

    fun getEventsByBoundsAndActionId(
        bounds: LatLngBoundsModel,
        actionId: Long
    ): Flowable<List<EventEntity>>

}