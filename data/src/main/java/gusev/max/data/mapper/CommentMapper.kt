package gusev.max.data.mapper

import gusev.max.data.entity.CommentEntity
import gusev.max.domain.model.Commentary
import javax.inject.Inject

/**
 * Created by v on 28/06/2018.
 */
class CommentMapper @Inject constructor() : EntityMapper<CommentEntity, Commentary> {

    override fun mapFromEntity(type: CommentEntity): Commentary {
        return Commentary(
                id = type.id,
                eventId = type.eventId,
                userId = type.userId,
                avatar = type.avatar,
                text = type.text,
                createdAt = type.createdAt,
                updatedAt = type.updatedAt
        )
    }

    override fun mapToEntity(type: Commentary): CommentEntity {
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

}