package gusev.max.cache.mapper

import gusev.max.cache.model.CachedComment
import gusev.max.data.entity.CommentEntity
import javax.inject.Inject

/**
 * Created by v on 28/06/2018.
 */
class CommentMapper @Inject constructor() : BaseCacheMapper<CachedComment, CommentEntity> {

    override fun mapFromCached(type: CachedComment): CommentEntity {
        return CommentEntity(
                id = type.id,
                eventId = type.eventId,
                userId = type.userId,
                avatar = type.avatar,
                text = type.text,
                createdAt = type.createdAt,
                updatedAt = type.updatedAt
        )
    }

    override fun mapToCached(type: CommentEntity): CachedComment {
        return CachedComment(
                id = type.id,
                eventId = type.eventId,
                userId = type.userId,
                avatar = type.avatar,
                text = type.text,
                createdAt = type.createdAt,
                updatedAt = type.updatedAt
        )
    }

}