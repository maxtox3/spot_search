package gusev.max.data.sourcefactory

import gusev.max.data.entity.ActionEntity
import gusev.max.data.source.action.ActionCache
import gusev.max.data.source.action.ActionCacheDataStore
import gusev.max.data.source.action.ActionDataStore
import gusev.max.data.source.action.ActionRemoteDataStore
import javax.inject.Inject

/**
 * Created by v on 13/06/2018.
 */
class ActionDataStoreFactory @Inject constructor(
    cacheStore: ActionCache,
    remoteDataStore: ActionRemoteDataStore,
    cacheDataStore: ActionCacheDataStore
) : BaseDataStoreFactory<ActionEntity, ActionDataStore, ActionCache>(
        cache = cacheStore,
        remoteStore = remoteDataStore,
        cacheStore = cacheDataStore
)