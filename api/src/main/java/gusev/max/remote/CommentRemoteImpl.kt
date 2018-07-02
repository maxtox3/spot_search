package gusev.max.remote

import gusev.max.data.entity.CommentEntity
import gusev.max.data.source.event.comment.CommentRemote
import gusev.max.remote.api.CommentApi
import gusev.max.remote.mapper.CommentMapper
import gusev.max.remote.model.CommentModel
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by v on 28/06/2018.
 */
class CommentRemoteImpl @Inject constructor(
    private val service: CommentApi,
    commentMapper: CommentMapper
) : BaseRemoteImpl<CommentEntity, CommentModel>(commentMapper), CommentRemote {

    override fun getCommentsByEventId(eventId: Long): Flowable<List<CommentEntity>> {
        return service.getCommentsByEventId(eventId)
            .map {
                it.entities?.map {
                    mapper.mapFromRemote(it)
                } ?: arrayListOf()
            }
    }

}