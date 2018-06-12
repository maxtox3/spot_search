package gusev.max.remote.mapper

import gusev.max.data.entity.UserEntity
import gusev.max.remote.model.UserModel
import javax.inject.Inject

/**
 * Created by v on 11/06/2018.
 */
class UserMapper @Inject constructor() : Mapper<UserModel, UserEntity> {

    override fun mapFromRemote(type: UserModel): UserEntity {
        return UserEntity(
                token = type.token ?: "",
                id = type.id,
                name = type.name,
                email = type.email,
                password = "",
                avatar = type.avatar ?: "",
                createdAt = type.createdAt ?: "",
                updatedAt = type.updatedAt ?: ""
        )
    }

}