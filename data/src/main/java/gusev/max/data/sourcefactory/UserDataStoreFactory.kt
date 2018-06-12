package gusev.max.data.sourcefactory

import gusev.max.data.entity.UserEntity
import gusev.max.data.source.user.UserCache
import gusev.max.data.source.user.UserCacheDataStore
import gusev.max.data.source.user.UserDataStore
import gusev.max.data.source.user.UserRemoteDataStore
import javax.inject.Inject

/**
 * Created by v on 10/06/2018.
 */
class UserDataStoreFactory @Inject constructor(
    cacheStore: UserCache,
    remoteDataStore: UserRemoteDataStore,
    cacheDataStore: UserCacheDataStore
) : BaseDataStoreFactory<UserEntity, UserDataStore, UserCache>(
        cache = cacheStore,
        remoteStore = remoteDataStore,
        cacheStore = cacheDataStore
)