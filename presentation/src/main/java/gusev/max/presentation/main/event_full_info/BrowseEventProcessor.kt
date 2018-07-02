package gusev.max.presentation.main.event_full_info

import gusev.max.domain.interactor.main.event.GetEvent
import io.reactivex.ObservableTransformer
import javax.inject.Inject

/**
 * Created by v on 27/06/2018.
 */
class BrowseEventProcessor @Inject constructor(
    private val getEvent: GetEvent
) {
    private val listProcessor: ObservableTransformer<EventFullInfoAction.BrowseEvent, EventFullInfoResult> = ObservableTransformer {
        it.switchMap {
            getEvent.execute(it.eventId)
                .map {
                    EventFullInfoResult.BrowseEvent.success(it)
                }
                .onErrorReturn {
                    EventFullInfoResult.BrowseEvent.failure()
                }
                .toObservable()
                .startWith(EventFullInfoResult.BrowseEvent.inFlight())
        }
    }
    var actionProcessor: ObservableTransformer<EventFullInfoAction, EventFullInfoResult>

    init {
        this.actionProcessor = ObservableTransformer {
            it.publish {
                it.ofType(EventFullInfoAction.BrowseEvent::class.java)
                    .compose(listProcessor)
            }
        }
    }
}