package gusev.max.presentation.main.map

import gusev.max.domain.interactor.main.event.GetEvents
import io.reactivex.ObservableTransformer
import javax.inject.Inject

/**
 * Created by v on 09/06/2018.
 */
class BrowseEventsProcessor @Inject constructor(
    private val getEvents: GetEvents
) {
    private val listProcessor: ObservableTransformer<MapAction.BrowseEvents, MapResult> = ObservableTransformer {
        it.switchMap {
            getEvents.execute(it.bounds, it.actionId)
                .map {
                    MapResult.BrowseEvents.success(
                            it
                    )
                }
                .onErrorReturn {
                    MapResult.BrowseEvents.failure()
                }
                .toObservable()
                .startWith(MapResult.BrowseEvents.inFlight())
        }
    }
    var actionProcessor: ObservableTransformer<MapAction, MapResult>

    init {
        this.actionProcessor = ObservableTransformer {
            it.publish {
                it.ofType(MapAction.BrowseEvents::class.java)
                    .compose(listProcessor)
            }
        }
    }
}