package gusev.max.data.repository

import gusev.max.data.entity.UserEntity
import gusev.max.data.mapper.UserMapper
import gusev.max.data.source.user.UserCache
import gusev.max.data.source.user.UserDataStore
import gusev.max.data.sourcefactory.UserDataStoreFactory
import gusev.max.domain.model.User
import gusev.max.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Created by v on 10/06/2018.
 */
class UserDataRepository @Inject constructor(
    dataStoreFactory: UserDataStoreFactory,
    entityMapper: UserMapper
) : BaseDataRepository<UserEntity, User, UserDataStore, UserCache>(dataStoreFactory, entityMapper),
        UserRepository