package gusev.max.data.mapper

import gusev.max.data.entity.UserEntity
import gusev.max.domain.model.User
import javax.inject.Inject

/**
 * Created by v on 10/06/2018.
 */
class UserMapper @Inject constructor() : EntityMapper<UserEntity, User> {

    override fun mapFromEntity(type: UserEntity): User {
        return User(
                id = type.id,
                name = type.name,
                email = type.email,
                password = type.password,
                avatar = type.avatar,
                createdAt = type.createdAt,
                updatedAt = type.updatedAt
        )
    }

    override fun mapToEntity(type: User): UserEntity {
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

}