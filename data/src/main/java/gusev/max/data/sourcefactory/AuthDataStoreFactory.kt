package gusev.max.data.sourcefactory

import gusev.max.data.source.auth.AuthCache
import gusev.max.data.source.auth.AuthCacheDataStore
import gusev.max.data.source.auth.AuthDataStore
import gusev.max.data.source.auth.AuthRemoteDataStore
import javax.inject.Inject

/**
 * Created by v on 11/06/2018.
 */
open class AuthDataStoreFactory @Inject constructor(
    private val remoteDataStore: AuthRemoteDataStore,
    private val cacheDataStore: AuthCacheDataStore,
    private val cache: AuthCache
) {

    open fun retrieveDataStore(isCached: Boolean): AuthDataStore {
        if (isCached && !cache.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    open fun retrieveCacheDataStore(): AuthDataStore {
        return cacheDataStore
    }

    open fun retrieveRemoteDataStore(): AuthDataStore {
        return remoteDataStore
    }
}