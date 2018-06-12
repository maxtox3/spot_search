package gusev.max.domain.model

/**
 * Created by v on 10/06/2018.
 */
data class User(
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
    val avatar: String,
    val createdAt: String,
    val updatedAt: String
) : BaseDomainModel