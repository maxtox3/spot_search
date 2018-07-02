package gusev.max.remote

import gusev.max.data.entity.EventEntity
import gusev.max.data.source.event.EventRemote
import gusev.max.domain.model.map.LatLngBoundsModel
import gusev.max.remote.api.EventApi
import gusev.max.remote.mapper.EventMapper
import gusev.max.remote.mapper.PojoMapper
import gusev.max.remote.model.EventModel
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by v on 11/06/2018.
 */
class EventRemoteImpl @Inject constructor(
    private val service: EventApi,
    private val eventMapper: EventMapper,
    private val postMapper: PojoMapper<EventModel>
) : BaseRemoteImpl<EventEntity, EventModel>(eventMapper), EventRemote {

    override fun saveEntity(entity: EventEntity): Completable {
        return service.saveEvent(eventMapper.mapToSaveModel(entity))
    }

    override fun getEntityById(id: Long): Flowable<EventEntity> {
        return service.getEventById(id)
            .map {
                eventMapper.mapFromRemote(it.entity!!)
            }
    }

    override fun getEventsByBounds(bounds: LatLngBoundsModel): Flowable<List<EventEntity>> {
        return service.getEventsByLatLngBounds(
                north = bounds.northEast.latitude,
                east = bounds.northEast.longitude,
                south = bounds.southWest.latitude,
                west = bounds.southWest.longitude
        )
            .map {
                it.entities?.map {
                    eventMapper.mapFromRemote(it)
                } ?: arrayListOf()
            }
    }

    override fun getEventsByBoundsAndActionId(
        bounds: LatLngBoundsModel,
        actionId: Long
    ): Flowable<List<EventEntity>> {
        if (actionId == -1L) {
            return getEventsByBounds(bounds)
        } else {
            return service.getEventsByLatLngBoundsAndActionId(
                    north = bounds.northEast.latitude,
                    east = bounds.northEast.longitude,
                    south = bounds.southWest.latitude,
                    west = bounds.southWest.longitude,
                    actionId = actionId
            )
                .map {
                    it.entities?.map {
                        eventMapper.mapFromRemote(it)
                    } ?: arrayListOf()
                }
        }
    }

}