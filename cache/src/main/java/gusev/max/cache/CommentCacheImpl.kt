package gusev.max.cache

import gusev.max.cache.dao.BaseDao
import gusev.max.cache.mapper.CommentMapper
import gusev.max.cache.model.CachedComment
import gusev.max.cache.storage.PreferencesHelper
import gusev.max.cache.storage.SpotSearchDatabase
import gusev.max.data.entity.CommentEntity
import gusev.max.data.source.event.comment.CommentCache
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by v on 28/06/2018.
 */
class CommentCacheImpl @Inject constructor(
    private val database: SpotSearchDatabase,
    private val preferencesHelper: PreferencesHelper,
    mapper: CommentMapper
) : BaseCacheImpl<CommentEntity, CachedComment>(mapper), CommentCache {

    override fun getCommentsByEventId(eventId: Long): Flowable<List<CommentEntity>> {
        return Flowable.defer {
            Flowable.just(
                    database.cachedCommentsDao().getCommentsByEventId(eventId)
            ).map {
                it.map {
                    entityMapper.mapFromCached(it)
                }
            }
        }
    }

    override fun setLastCacheTime(lastCacheTime: Long) {
        preferencesHelper.lastCommentsCacheTime = lastCacheTime
    }

    override fun getDao(): BaseDao<CachedComment> {
        return database.cachedCommentsDao()
    }

    override fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCommentsCacheTime
    }

}