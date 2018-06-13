package gusev.max.data.mapper

import gusev.max.data.entity.ActionEntity
import gusev.max.domain.model.Action
import javax.inject.Inject

/**
 * Created by v on 13/06/2018.
 */
class ActionMapper @Inject constructor() : EntityMapper<ActionEntity, Action> {

    override fun mapFromEntity(type: ActionEntity): Action {
        return Action(
                id = type.id,
                name = type.name,
                description = type.description,
                primaryColor = type.primaryColor,
                secondaryColor = type.secondaryColor
        )
    }

    override fun mapToEntity(type: Action): ActionEntity {
        return ActionEntity(
                id = type.id,
                name = type.name,
                description = type.description,
                primaryColor = type.primaryColor,
                secondaryColor = type.secondaryColor
        )
    }

}