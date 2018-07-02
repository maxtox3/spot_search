package gusev.max.presentation.main.actions

import io.reactivex.Observable

/**
 * Created by v on 15/06/2018.
 */
interface BrowseActionsView {

    fun actionsIntent(): Observable<ActionsIntent>

    fun render(state: ActionsViewState)
}