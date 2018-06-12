package gusev.max.remote.mapper

import gusev.max.data.entity.EventEntity
import gusev.max.remote.model.EventModel
import javax.inject.Inject

/**
 * Created by v on 11/06/2018.
 */
class EventMapper @Inject constructor() : Mapper<EventModel, EventEntity> {

    override fun mapFromRemote(type: EventModel): EventEntity {
        return EventEntity(
                id = type.id,
                userId = type.userId,
                name = type.name,
                description = type.description,
                photoUrl = type.photoUrl ?: "",
                latitude = type.lat,
                longitude = type.lng,
                likesCount = type.likesCount,
                dislikesCount = type.dislikesCount
        )
    }

}