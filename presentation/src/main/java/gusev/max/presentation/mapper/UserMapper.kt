package gusev.max.presentation.mapper

import gusev.max.domain.model.User
import gusev.max.presentation.model.UserModel
import javax.inject.Inject

/**
 * Created by v on 11/06/2018.
 */
class UserMapper @Inject constructor() : Mapper<UserModel, User> {

    override fun mapToViewModel(entity: User): UserModel {
        return UserModel(
                id = entity.id,
                name = entity.name,
                email = entity.email,
                password = entity.password,
                avatar = entity.avatar,
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt
        )
    }

    override fun mapToDomainModel(model: UserModel): User {
        return User(
                id = model.id,
                name = model.name,
                email = model.email,
                password = model.password,
                avatar = model.avatar,
                createdAt = model.createdAt,
                updatedAt = model.updatedAt
        )
    }

    fun mapToLoginModel(email: String, password: String): UserModel {
        return UserModel(
                id = -1,
                name = "",
                email = email,
                password = password,
                avatar = "",
                createdAt = "",
                updatedAt = ""
        )
    }

    fun mapToSignUpModel(name: String, email: String, password: String): UserModel {
        return UserModel(
                id = -1,
                name = name,
                email = email,
                password = password,
                avatar = "",
                createdAt = "",
                updatedAt = ""
        )
    }

}