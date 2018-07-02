package gusev.max.presentation.main.event_full_info

import gusev.max.domain.interactor.main.event.GetComments
import io.reactivex.ObservableTransformer
import javax.inject.Inject

/**
 * Created by v on 27/06/2018.
 */
class BrowseCommentsProcessor @Inject constructor(
    private val getComments: GetComments
) {
    private val listProcessor: ObservableTransformer<EventFullInfoAction.BrowseComments, EventFullInfoResult> = ObservableTransformer {
        it.switchMap {
            getComments.execute(it.eventId)
                .map {
                    EventFullInfoResult.BrowseComments.success(it)
                }
                .onErrorReturn {
                    EventFullInfoResult.BrowseComments.failure()
                }
                .toObservable()
                .startWith(EventFullInfoResult.BrowseComments.inFlight())
        }
    }
    var actionProcessor: ObservableTransformer<EventFullInfoAction, EventFullInfoResult>

    init {
        this.actionProcessor = ObservableTransformer {
            it.publish {
                it.ofType(EventFullInfoAction.BrowseComments::class.java)
                    .compose(listProcessor)
            }
        }
    }
}