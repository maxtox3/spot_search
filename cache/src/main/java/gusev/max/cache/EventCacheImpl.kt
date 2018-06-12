package gusev.max.cache

import gusev.max.cache.dao.BaseDao
import gusev.max.cache.mapper.EventMapper
import gusev.max.cache.model.CachedEvent
import gusev.max.cache.storage.PreferencesHelper
import gusev.max.cache.storage.SpotSearchDatabase
import gusev.max.data.entity.EventEntity
import gusev.max.data.source.event.EventCache
import gusev.max.domain.model.map.LatLngBoundsModel
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by v on 10/06/2018.
 */
class EventCacheImpl @Inject constructor(
    private val database: SpotSearchDatabase,
    private val preferencesHelper: PreferencesHelper,
    mapper: EventMapper
) : BaseCacheImpl<EventEntity, CachedEvent>(mapper), EventCache {

    override fun getEntitiesByBounds(bounds: LatLngBoundsModel): Flowable<List<EventEntity>> {
        return Flowable.defer {
            Flowable.just(
                    database.cachedEventsDao().getEventsByLatLngBounds(
                            northLatitude = bounds.northEast.latitude,
                            southLatitude = bounds.southWest.latitude,
                            westLongitude = bounds.southWest.longitude,
                            eastLongitude = bounds.northEast.longitude
                    )
            ).map {
                it.map {
                    entityMapper.mapFromCached(it)
                }
            }
        }
    }

    override fun setLastCacheTime(lastCacheTime: Long) {
        preferencesHelper.lastEventsCacheTime = lastCacheTime
    }

    override fun getDao(): BaseDao<CachedEvent> {
        return database.cachedEventsDao()
    }

    override fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastEventsCacheTime
    }

}