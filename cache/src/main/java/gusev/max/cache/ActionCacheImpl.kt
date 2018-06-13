package gusev.max.cache

import gusev.max.cache.dao.BaseDao
import gusev.max.cache.mapper.ActionMapper
import gusev.max.cache.model.CachedAction
import gusev.max.cache.storage.PreferencesHelper
import gusev.max.cache.storage.SpotSearchDatabase
import gusev.max.data.entity.ActionEntity
import gusev.max.data.source.action.ActionCache
import javax.inject.Inject

/**
 * Created by v on 13/06/2018.
 */
class ActionCacheImpl @Inject constructor(
    private val database: SpotSearchDatabase,
    private val preferencesHelper: PreferencesHelper,
    mapper: ActionMapper
) : BaseCacheImpl<ActionEntity, CachedAction>(mapper), ActionCache {

    override fun setLastCacheTime(lastCacheTime: Long) {
        preferencesHelper.lastActionsCacheTime = lastCacheTime
    }

    override fun getDao(): BaseDao<CachedAction> {
        return database.cachedActionsDao()
    }

    override fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastActionsCacheTime
    }

}