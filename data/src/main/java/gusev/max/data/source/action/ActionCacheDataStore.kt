package gusev.max.data.source.action

import gusev.max.data.entity.ActionEntity
import gusev.max.data.source.base.BaseCacheDataStore
import javax.inject.Inject

/**
 * Created by v on 13/06/2018.
 */
class ActionCacheDataStore @Inject constructor(
    cacheStore: ActionCache
) : BaseCacheDataStore<ActionEntity>(cacheStore), ActionDataStore