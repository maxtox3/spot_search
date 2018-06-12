package gusev.max.data.sourcefactory

import gusev.max.data.entity.EventEntity
import gusev.max.data.source.event.EventCache
import gusev.max.data.source.event.EventCacheDataStore
import gusev.max.data.source.event.EventDataStore
import gusev.max.data.source.event.EventRemoteDataStore
import javax.inject.Inject

/**
 * Created by v on 10/06/2018.
 */
class EventDataStoreFactory @Inject constructor(
    cacheStore: EventCache,
    remoteDataStore: EventRemoteDataStore,
    cacheDataStore: EventCacheDataStore
) : BaseDataStoreFactory<EventEntity, EventDataStore, EventCache>(
        cache = cacheStore,
        remoteStore = remoteDataStore,
        cacheStore = cacheDataStore
)