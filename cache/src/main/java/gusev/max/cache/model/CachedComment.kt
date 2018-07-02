package gusev.max.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import gusev.max.cache.dao.CachedCommentDao

/**
 * Created by v on 28/06/2018.
 */
@Entity(tableName = CachedCommentDao.TABLE_NAME)
data class CachedComment(

    @PrimaryKey
    val id: Long,
    val eventId: Long,
    val userId: Long,
    val avatar: String,
    val text: String,
    val createdAt: String,
    val updatedAt: String
) : BaseCachedModel