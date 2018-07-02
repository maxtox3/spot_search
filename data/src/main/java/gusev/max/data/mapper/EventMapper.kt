package gusev.max.data.mapper

import gusev.max.data.entity.EventEntity
import gusev.max.domain.model.Event
import gusev.max.domain.model.map.LatLngModel
import javax.inject.Inject

/**
 * Created by v on 10/06/2018.
 */
class EventMapper @Inject constructor() : EntityMapper<EventEntity, Event> {

    override fun mapToEntity(type: Event): EventEntity {
        return EventEntity(
                id = type.id,
                userId = type.userId,
                actionId = type.actionId,
                name = type.name,
                description = type.description,
                photoUrl = type.photoUrl,
                latitude = type.latLng.latitude,
                longitude = type.latLng.longitude,
                likesCount = type.likesCount,
                dislikesCount = type.dislikesCount
        )
    }

    override fun mapFromEntity(type: EventEntity): Event {
        return Event(
                id = type.id,
                userId = type.userId,
                actionId = type.actionId,
                name = type.name,
                description = type.description,
                photoUrl = type.photoUrl,
                latLng = LatLngModel(type.latitude, type.longitude),
                likesCount = type.likesCount,
                dislikesCount = type.dislikesCount
        )
    }


}