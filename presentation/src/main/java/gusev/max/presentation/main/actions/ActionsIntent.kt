package gusev.max.presentation.main.actions

import gusev.max.presentation.base.BaseIntent

/**
 * Created by v on 13/06/2018.
 */
sealed class ActionsIntent : BaseIntent {

    object InitialIntent : ActionsIntent()

    object BrowseActionsIntent : ActionsIntent()

    object TryAgain : ActionsIntent()
}