package gusev.max.presentation.main.actions

import gusev.max.presentation.base.BaseViewState
import gusev.max.presentation.model.ActionModel

/**
 * Created by v on 13/06/2018.
 */
sealed class ActionsViewState(
    val inProgress: Boolean = false,
    val actions: List<ActionModel>? = null
) : BaseViewState {

    object InProgress : ActionsViewState(true, null)

    object Failed : ActionsViewState(false, null)

    class BrowseActionsSuccess(actions: List<ActionModel>) : ActionsViewState(false, actions)

    object Idle : ActionsViewState(false, null)
}