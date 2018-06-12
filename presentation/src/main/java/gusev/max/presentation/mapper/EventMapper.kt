package gusev.max.presentation.mapper

import gusev.max.domain.model.Event
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
                name = model.name,
                description = model.description,
                photoUrl = model.photoUrl,
                latLng = model.latLng,
                likesCount = model.likesCount,
                dislikesCount = model.dislikesCount
        )
    }

}