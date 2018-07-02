package gusev.max.presentation.mapper

import gusev.max.domain.model.Commentary
import gusev.max.presentation.model.CommentaryModel
import javax.inject.Inject

/**
 * Created by v on 27/06/2018.
 */
class CommentMapper @Inject constructor() : Mapper<CommentaryModel, Commentary> {

    override fun mapToViewModel(entity: Commentary): CommentaryModel {
        return CommentaryModel(
                id = entity.id,
                eventId = entity.eventId,
                userId = entity.userId,
                avatar = entity.avatar,
                text = entity.text,
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt
        )
    }

    override fun mapToDomainModel(model: CommentaryModel): Commentary {
        return Commentary(
                id = model.id,
                eventId = model.eventId,
                userId = model.userId,
                avatar = model.avatar,
                text = model.text,
                createdAt = model.createdAt,
                updatedAt = model.updatedAt
        )
    }

}