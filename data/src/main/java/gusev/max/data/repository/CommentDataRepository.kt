package gusev.max.data.repository

import gusev.max.data.entity.CommentEntity
import gusev.max.data.mapper.CommentMapper
import gusev.max.data.source.event.comment.CommentCache
import gusev.max.data.source.event.comment.CommentDataStore
import gusev.max.data.sourcefactory.CommentDataStoreFactory
import gusev.max.domain.model.Commentary
import gusev.max.domain.repository.CommentsRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by v on 28/06/2018.
 */
class CommentDataRepository @Inject constructor(
    dataStoreFactory: CommentDataStoreFactory,
    private val entityMapper: CommentMapper
) : BaseDataRepository<CommentEntity, Commentary, CommentDataStore, CommentCache>(
        dataStoreFactory,
        entityMapper
), CommentsRepository {

    override fun getCommentsByEventId(eventId: Long): Flowable<List<Commentary>> {
        return factory.retrieveRemoteDataStore().getCommentsByEventId(eventId)
            .map {
                it.map {
                    mapper.mapFromEntity(it)
                }
            }
    }

    override fun likeCommentary(eventId: Long, commentaryId: Long): Completable {
        throw UnsupportedOperationException()
    }

    override fun dislikeCommentary(eventId: Long, commentaryId: Long): Completable {
        throw UnsupportedOperationException()
//        return factory.retrieveRemoteDataStore().dislikeCommentary(eventId, commentaryId)
    }

}