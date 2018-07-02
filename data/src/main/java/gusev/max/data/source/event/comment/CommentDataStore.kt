package gusev.max.data.source.event.comment

import gusev.max.data.entity.CommentEntity
import gusev.max.data.source.base.BaseDataStore
import io.reactivex.Flowable

/**
 * Created by v on 27/06/2018.
 */
interface CommentDataStore : BaseDataStore<CommentEntity> {

    fun getCommentsByEventId(eventId: Long): Flowable<List<CommentEntity>>
}