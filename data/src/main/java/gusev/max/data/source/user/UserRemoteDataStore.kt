package gusev.max.data.source.user

import gusev.max.data.entity.UserEntity
import gusev.max.data.source.base.BaseRemoteDataStore
import javax.inject.Inject


class UserRemoteDataStore @Inject constructor(
    remote: UserRemote
) : BaseRemoteDataStore<UserEntity>(remote), UserDataStore