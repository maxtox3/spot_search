package gusev.max.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import gusev.max.cache.model.CachedAction

/**
 * Created by v on 13/06/2018.
 */
@Dao
abstract class CachedActionDao : BaseDao<CachedAction> {

    companion object {
        const val TABLE_NAME = "actions"

        const val QUERY_ACTIONS = "SELECT * FROM $TABLE_NAME"

        const val DELETE_ACTIONS = "DELETE FROM $TABLE_NAME"

        const val QUERY_ACTION_BY_ID = "SELECT * FROM $TABLE_NAME WHERE id = :id"
    }

    @Query(QUERY_ACTIONS)
    abstract override fun getEntities(): List<CachedAction>

    @Query(QUERY_ACTION_BY_ID)
    abstract override fun getEntityById(id: Long): CachedAction

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract override fun saveEntity(cachedEntity: CachedAction)

    @Query(DELETE_ACTIONS)
    abstract override fun clearEntities()
}