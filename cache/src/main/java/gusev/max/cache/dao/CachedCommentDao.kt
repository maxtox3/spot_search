package gusev.max.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import gusev.max.cache.model.CachedComment

/**
 * Created by v on 28/06/2018.
 */
@Dao
abstract class CachedCommentDao : BaseDao<CachedComment> {

    companion object {
        const val TABLE_NAME = "Comments"

        const val QUERY_COMMENTS = "SELECT * FROM $TABLE_NAME"

        const val DELETE_COMMENTS = "DELETE FROM $TABLE_NAME"

        const val QUERY_COMMENT_BY_ID = "SELECT * FROM $TABLE_NAME WHERE id = :id"

        const val QUERY_COMMENTS_BY_EVENT_ID = "SELECT * FROM $TABLE_NAME WHERE eventId = :eventId"

    }

    @Query(QUERY_COMMENTS)
    abstract override fun getEntities(): List<CachedComment>

    @Query(QUERY_COMMENT_BY_ID)
    abstract override fun getEntityById(id: Long): CachedComment

    @Query(QUERY_COMMENTS_BY_EVENT_ID)
    abstract fun getCommentsByEventId(eventId: Long): List<CachedComment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract override fun saveEntity(cachedEntity: CachedComment)

    @Query(DELETE_COMMENTS)
    abstract override fun clearEntities()

}