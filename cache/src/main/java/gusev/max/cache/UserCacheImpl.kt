package gusev.max.cache

import gusev.max.cache.dao.BaseDao
import gusev.max.cache.mapper.UserMapper
import gusev.max.cache.model.CachedUser
import gusev.max.cache.storage.PreferencesHelper
import gusev.max.cache.storage.SpotSearchDatabase
import gusev.max.data.entity.UserEntity
import gusev.max.data.source.user.UserCache
import javax.inject.Inject

/**
 * Created by v on 10/06/2018.
 */
class UserCacheImpl @Inject constructor(
    private val database: SpotSearchDatabase,
    private val preferencesHelper: PreferencesHelper,
    mapper: UserMapper
) : BaseCacheImpl<UserEntity, CachedUser>(mapper), UserCache {

    override fun setLastCacheTime(lastCacheTime: Long) {
        preferencesHelper.lastUsersChangeTime = lastCacheTime
    }

    override fun getDao(): BaseDao<CachedUser> {
        return database.cachedUserDao()
    }

    override fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastUsersChangeTime
    }

}