package gusev.max.data.source.event.comment

import gusev.max.data.entity.CommentEntity
import gusev.max.data.source.base.BaseCacheDataStore
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by v on 27/06/2018.
 */
class CommentCacheDataStore @Inject constructor(
    private val cacheStore: CommentCache
) : BaseCacheDataStore<CommentEntity>(cacheStore), CommentDataStore {

    override fun getCommentsByEventId(eventId: Long): Flowable<List<CommentEntity>> {
        return cacheStore.getCommentsByEventId(eventId)
    }

}