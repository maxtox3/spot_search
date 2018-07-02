package gusev.max.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import gusev.max.cache.dao.CachedEventDao

/**
 * Created by v on 10/06/2018.
 */
@Entity(tableName = CachedEventDao.TABLE_NAME)
data class CachedEvent(

    @PrimaryKey
    val id: Long,
    val userId: Long,
    val actionId: Long,
    val name: String,
    val description: String,
    val photoUrl: String,
    val latitude: Double,
    val longitude: Double,
    val likesCount: Int,
    val dislikesCount: Int

) : BaseCachedModel