package gusev.max.presentation.main.map

import gusev.max.domain.interactor.main.event.CreateEvent
import io.reactivex.ObservableTransformer
import javax.inject.Inject

/**
 * Created by v on 15/06/2018.
 */
class CreateEventProcessor @Inject constructor(
    private val createEvent: CreateEvent
) {
    private val createProcessor: ObservableTransformer<MapAction.CreateNewEvent, MapResult> = ObservableTransformer {
        it.switchMap {
            createEvent.execute(it.event)
                .toSingleDefault(MapResult.CreateEvent.success())
                .onErrorReturn {
                    MapResult.CreateEvent.failure()
                }
                .toObservable()
                .startWith(MapResult.CreateEvent.inFlight())
        }
    }
    var actionProcessor: ObservableTransformer<MapAction, MapResult>

    init {
        this.actionProcessor = ObservableTransformer {
            it.publish {
                it.ofType(MapAction.CreateNewEvent::class.java)
                    .compose(createProcessor)
            }
        }
    }
}