package gusev.max.remote.mapper

import gusev.max.data.entity.ActionEntity
import gusev.max.remote.model.ActionModel
import javax.inject.Inject

/**
 * Created by v on 13/06/2018.
 */
class ActionMapper @Inject constructor() : Mapper<ActionModel, ActionEntity> {

    companion object {
        const val DEFAULT_COLOR = "#4F70C1"
    }

    override fun mapFromRemote(type: ActionModel): ActionEntity {
        return ActionEntity(
                id = type.id,
                name = type.name ?: "",
                description = type.description ?: "",
                primaryColor = type.primaryColor ?: DEFAULT_COLOR,
                secondaryColor = type.secondaryColor ?: DEFAULT_COLOR
        )
    }

}