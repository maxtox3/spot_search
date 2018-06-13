package gusev.max.presentation.model

/**
 * Created by v on 13/06/2018.
 */
data class ActionModel(
    val id: Long,
    val name: String,
    val description: String,
    val primaryColor: String,
    val secondaryColor: String
) : BaseModel