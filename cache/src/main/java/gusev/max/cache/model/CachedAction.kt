package gusev.max.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import gusev.max.cache.dao.CachedActionDao

/**
 * Created by v on 13/06/2018.
 */
@Entity(tableName = CachedActionDao.TABLE_NAME)
data class CachedAction(
    @PrimaryKey
    val id: Long,
    val name: String,
    val description: String,
    val primaryColor: String,
    val secondaryColor: String
) : BaseCachedModel