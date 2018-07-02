package gusev.max.presentation.main.actions

import gusev.max.domain.interactor.main.action.GetActions
import io.reactivex.ObservableTransformer
import javax.inject.Inject

/**
 * Created by v on 13/06/2018.
 */
class BrowseActionsProcessor @Inject constructor(
    private val getActions: GetActions
) {
    private val listProcessor: ObservableTransformer<ActionsAction.BrowseActions, ActionsResult> = ObservableTransformer {
        it.switchMap {
            getActions.execute()
                .map {
                    ActionsResult.BrowseActions.success(
                            it
                    )
                }
                .onErrorReturn {
                    ActionsResult.BrowseActions.failure()
                }
                .toObservable()
                .startWith(ActionsResult.BrowseActions.inFlight())
        }
    }
    var actionProcessor: ObservableTransformer<ActionsAction, ActionsResult>

    init {
        this.actionProcessor = ObservableTransformer {
            it.publish {
                it.ofType(ActionsAction.BrowseActions::class.java)
                    .compose(listProcessor)
            }
        }
    }
}