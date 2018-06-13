package gusev.max.presentation.mapper

import gusev.max.domain.model.Action
import gusev.max.presentation.model.ActionModel
import javax.inject.Inject

/**
 * Created by v on 13/06/2018.
 */
class ActionMapper @Inject constructor() : Mapper<ActionModel, Action> {

    override fun mapToViewModel(entity: Action): ActionModel {
        return ActionModel(
                id = entity.id,
                name = entity.name,
                description = entity.description,
                primaryColor = entity.primaryColor,
                secondaryColor = entity.secondaryColor
        )
    }

    override fun mapToDomainModel(model: ActionModel): Action {
        return Action(
                id = model.id,
                name = model.name,
                description = model.description,
                primaryColor = model.primaryColor,
                secondaryColor = model.secondaryColor
        )
    }

}