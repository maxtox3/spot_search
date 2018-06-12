package gusev.max.cache.mapper

import gusev.max.cache.model.CachedUser
import gusev.max.data.entity.UserEntity
import javax.inject.Inject

/**
 * Created by v on 10/06/2018.
 */
class UserMapper @Inject constructor() : BaseCacheMapper<CachedUser, UserEntity> {

    override fun mapFromCached(type: CachedUser): UserEntity {
        return UserEntity(
                token = "",
                id = type.id,
                name = type.name,
                email = type.email,
                password = type.password,
                avatar = type.avatar,
                createdAt = type.createdAt,
                updatedAt = type.updatedAt
        )
    }

    override fun mapToCached(type: UserEntity): CachedUser {
        return CachedUser(
                id = type.id,
                name = type.name,
                email = type.email,
                password = type.password,
                avatar = type.avatar,
                createdAt = type.createdAt,
                updatedAt = type.updatedAt
        )
    }

}