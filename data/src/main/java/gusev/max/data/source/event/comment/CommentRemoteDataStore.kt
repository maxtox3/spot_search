package gusev.max.data.source.event.comment

import gusev.max.data.entity.CommentEntity
import gusev.max.data.source.base.BaseRemoteDataStore
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by v on 27/06/2018.
 */
class CommentRemoteDataStore @Inject constructor(
    private val remoteStore: CommentRemote
) : BaseRemoteDataStore<CommentEntity>(remoteStore), CommentDataStore {

    override fun getCommentsByEventId(eventId: Long): Flowable<List<CommentEntity>> {
        return remoteStore.getCommentsByEventId(eventId)
    }
}