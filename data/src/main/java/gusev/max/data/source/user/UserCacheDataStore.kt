package gusev.max.data.source.user

import gusev.max.data.entity.UserEntity
import gusev.max.data.source.base.BaseCacheDataStore
import javax.inject.Inject


class UserCacheDataStore @Inject constructor(
    cache: UserCache
) : BaseCacheDataStore<UserEntity>(cache), UserDataStore