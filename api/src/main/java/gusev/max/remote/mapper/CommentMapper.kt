package gusev.max.remote.mapper

import gusev.max.data.entity.CommentEntity
import gusev.max.remote.model.CommentModel
import javax.inject.Inject

/**
 * Created by v on 28/06/2018.
 */
class CommentMapper @Inject constructor() : Mapper<CommentModel, CommentEntity> {

    override fun mapFromRemote(type: CommentModel): CommentEntity {
        return CommentEntity(
                id = type.id,
                eventId = type.eventId,
                userId = type.userId,
                avatar = type.avatar ?: "",
                text = type.text ?: "",
                createdAt = type.createdAt ?: "",
                updatedAt = type.updatedAt ?: ""
        )
    }

}