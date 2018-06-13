package gusev.max.data.entity

/**
 * Created by v on 13/06/2018.
 */
data class ActionEntity(
    val id: Long,
    val name: String,
    val description: String,
    val primaryColor: String,
    val secondaryColor: String
) : BaseEntity