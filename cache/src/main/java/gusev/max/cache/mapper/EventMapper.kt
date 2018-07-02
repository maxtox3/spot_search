package gusev.max.cache.mapper

import gusev.max.cache.model.CachedEvent
import gusev.max.data.entity.EventEntity
import javax.inject.Inject

/**
 * Created by v on 10/06/2018.
 */
class EventMapper @Inject constructor() : BaseCacheMapper<CachedEvent, EventEntity> {
    override fun mapFromCached(type: CachedEvent): EventEntity {
        return EventEntity(
                id = type.id,
                userId = type.userId,
                actionId = type.actionId,
                name = type.name,
                description = type.description,
                photoUrl = type.photoUrl,
                latitude = type.latitude,
                longitude = type.longitude,
                likesCount = type.likesCount,
                dislikesCount = type.dislikesCount
        )
    }

    override fun mapToCached(type: EventEntity): CachedEvent {
        return CachedEvent(
                id = type.id,
                userId = type.userId,
                actionId = type.actionId,
                name = type.name,
                description = type.description,
                photoUrl = type.photoUrl,
                latitude = type.latitude,
                longitude = type.longitude,
                likesCount = type.likesCount,
                dislikesCount = type.dislikesCount
        )
    }

}