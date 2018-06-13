package gusev.max.presentation.model

/**
 * Created by v on 11/06/2018.
 */
data class UserModel(
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
    val avatar: String,
    val createdAt: String,
    val updatedAt: String
) : BaseModel