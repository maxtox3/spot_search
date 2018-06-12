package gusev.max.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import gusev.max.cache.dao.CachedUserDao

/**
 * Created by v on 10/06/2018.
 */
@Entity(tableName = CachedUserDao.TABLE_NAME)
data class CachedUser(

    @PrimaryKey
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
    val avatar: String,
    val createdAt: String,
    val updatedAt: String

) : BaseCachedModel