package gusev.max.presentation.map

import gusev.max.domain.interactor.main.GetEvents
import io.reactivex.ObservableTransformer
import javax.inject.Inject

/**
 * Created by v on 09/06/2018.
 */
class BrowseEventsProcessor @Inject constructor(
    private val getTasks: GetEvents
) {
    private val listProcessor: ObservableTransformer<BrowseEventsAction.BrowseEvents, BrowseEventsResult> = ObservableTransformer {
        it.switchMap {
            getTasks.execute(it.bounds)
                .map {
                    BrowseEventsResult.BrowseEvents.success(it)
                }
                .onErrorReturn {
                    BrowseEventsResult.BrowseEvents.failure()
                }
                .toObservable()
                .startWith(BrowseEventsResult.BrowseEvents.inFlight())
        }
    }
    var actionProcessor: ObservableTransformer<BrowseEventsAction, BrowseEventsResult>

    init {
        this.actionProcessor = ObservableTransformer {
            it.publish {
                it.ofType(BrowseEventsAction.BrowseEvents::class.java)
                    .compose(listProcessor)
            }
        }
    }
}