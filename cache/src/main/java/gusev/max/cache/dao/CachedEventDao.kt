package gusev.max.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import gusev.max.cache.model.CachedEvent

/**
 * Created by v on 10/06/2018.
 */
@Dao
abstract class CachedEventDao : BaseDao<CachedEvent> {

    companion object {
        const val TABLE_NAME = "events"

        const val QUERY_EVENTS = "SELECT * FROM $TABLE_NAME"

        const val DELETE_EVENTS = "DELETE FROM $TABLE_NAME"

        const val QUERY_EVENT_BY_ID = "SELECT * FROM $TABLE_NAME WHERE id = :id"

        const val QUERY_EVENT_BY_LAT_LNG = "SELECT * FROM $TABLE_NAME WHERE" +
                " latitude <= :northLatitude AND" +
                " latitude >= :southLatitude AND" +
                " longitude <= :eastLongitude AND" +
                " longitude >= :westLongitude"

    }

    @Query(QUERY_EVENTS)
    abstract override fun getEntities(): List<CachedEvent>

    @Query(QUERY_EVENT_BY_ID)
    abstract override fun getEntityById(id: Long): CachedEvent

    @Query(QUERY_EVENT_BY_LAT_LNG)
    abstract fun getEventsByLatLngBounds(
        northLatitude: Double,
        southLatitude: Double,
        westLongitude: Double,
        eastLongitude: Double
    ): List<CachedEvent>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract override fun saveEntity(cachedEntity: CachedEvent)

    @Query(DELETE_EVENTS)
    abstract override fun clearEntities()

}