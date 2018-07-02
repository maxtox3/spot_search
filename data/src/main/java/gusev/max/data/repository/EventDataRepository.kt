package gusev.max.data.repository

import gusev.max.data.entity.EventEntity
import gusev.max.data.mapper.EventMapper
import gusev.max.data.source.event.EventCache
import gusev.max.data.source.event.EventDataStore
import gusev.max.data.sourcefactory.EventDataStoreFactory
import gusev.max.domain.model.Event
import gusev.max.domain.model.map.LatLngBoundsModel
import gusev.max.domain.repository.EventsRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by v on 10/06/2018.
 */
class EventDataRepository @Inject constructor(
    dataStoreFactory: EventDataStoreFactory,
    entityMapper: EventMapper
) : BaseDataRepository<EventEntity, Event, EventDataStore, EventCache>(
        dataStoreFactory,
        entityMapper
), EventsRepository {

    override fun getModelsByBounds(bounds: LatLngBoundsModel): Flowable<List<Event>> {
        return factory.retrieveRemoteDataStore().getEventsByBounds(bounds)
            .flatMap {
                Flowable.just(it.map {
                    mapper.mapFromEntity(it)
                })
            }
    }

    override fun getModelsByBoundsAndActionId(
        bounds: LatLngBoundsModel,
        actionId: Long
    ): Flowable<List<Event>> {
        return factory.retrieveRemoteDataStore().getEventsByBoundsAndActionId(bounds, actionId)
            .flatMap {
                Flowable.just(it.map {
                    mapper.mapFromEntity(it)
                })
            }

    }

}