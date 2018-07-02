package gusev.max.presentation.main.map

import gusev.max.domain.interactor.main.action.GetActions
import io.reactivex.ObservableTransformer
import javax.inject.Inject

/**
 * Created by v on 19/06/2018.
 */
class BrowseActionsProcessor @Inject constructor(
    private val getActions: GetActions
) {
    private val listProcessor: ObservableTransformer<MapAction.BrowseActions, MapResult> = ObservableTransformer {
        it.switchMap {
            getActions.execute()
                .map {
                    MapResult.BrowseActions.success(it)
                }
                .onErrorReturn {
                    MapResult.BrowseActions.failure()
                }
                .toObservable()
                .startWith(MapResult.BrowseActions.inFlight())
        }
    }
    var actionProcessor: ObservableTransformer<MapAction, MapResult>

    init {
        this.actionProcessor = ObservableTransformer {
            it.publish {
                it.ofType(MapAction.BrowseActions::class.java)
                    .compose(listProcessor)
            }
        }
    }
}