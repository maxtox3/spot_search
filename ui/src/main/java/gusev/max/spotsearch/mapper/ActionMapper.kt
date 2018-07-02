package gusev.max.spotsearch.mapper

import gusev.max.presentation.model.ActionModel
import gusev.max.spotsearch.model.ActionUiModel
import javax.inject.Inject

/**
 * Created by v on 23/06/2018.
 */
class ActionMapper @Inject constructor() : Mapper<ActionModel, ActionUiModel> {

    override fun mapToUIModel(presentationModel: ActionModel): ActionUiModel {
        return ActionUiModel(
                id = presentationModel.id,
                name = presentationModel.name,
                description = presentationModel.description,
                primaryColor = presentationModel.primaryColor,
                secondaryColor = presentationModel.secondaryColor
        )
    }

    override fun mapToPresentationModel(uiModel: ActionUiModel): ActionModel {
        return ActionModel(
                id = uiModel.id,
                name = uiModel.name,
                description = uiModel.description,
                primaryColor = uiModel.primaryColor,
                secondaryColor = uiModel.secondaryColor
        )
    }

}