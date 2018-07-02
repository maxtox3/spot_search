package gusev.max.spotsearch.model

/**
 * Created by v on 23/06/2018.
 */
data class ActionUiModel(
    val id: Long,
    val name: String,
    val description: String,
    val primaryColor: String,
    val secondaryColor: String,
    override var chosen: Boolean = false
) : BaseUIModel