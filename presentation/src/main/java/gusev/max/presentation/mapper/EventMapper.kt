package gusev.max.presentation.mapper

import gusev.max.domain.model.Event
import gusev.max.domain.model.map.LatLngModel
import gusev.max.presentation.model.EventModel
import javax.inject.Inject

/**
 * Created by v on 10/06/2018.
 */
class EventMapper @Inject constructor() : Mapper<EventModel, Event> {

    override fun mapToViewModel(entity: Event): EventModel {
        return EventModel(
                id = entity.id,
                userId = entity.userId,
                actionId = entity.actionId,
                name = entity.name,
                description = entity.description,
                photoUrl = entity.photoUrl,
                latLng = entity.latLng,
                likesCount = entity.likesCount,
                dislikesCount = entity.dislikesCount
        )
    }

    override fun mapToDomainModel(model: EventModel): Event {
        return Event(
                id = model.id,
                userId = model.userId,
                actionId = model.actionId,
                name = model.name,
                description = model.description,
                photoUrl = model.photoUrl,
                latLng = model.latLng,
                likesCount = model.likesCount,
                dislikesCount = model.dislikesCount
        )
    }

    fun mapToCreateModel(
        actionId: Long,
        name: String,
        description: String,
        latLngModel: LatLngModel
    ): Event {
        return Event(
                id = -1,
                userId = -1,
                actionId = actionId,
                name = name,
                description = description,
                photoUrl = "",
                latLng = latLngModel,
                likesCount = 0,
                dislikesCount = 0
        )
    }

}