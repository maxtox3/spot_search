package gusev.max.presentation.main.map

import android.arch.lifecycle.ViewModel
import gusev.max.presentation.base.BaseViewModel
import gusev.max.presentation.base.model.TaskStatus
import gusev.max.presentation.mapper.EventMapper
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by v on 09/06/2018.
 */
open class BrowseEventsViewModel @Inject constructor(
    private val browseProcessor: BrowseEventsProcessor,
    private val eventMapper: EventMapper
) : ViewModel(), BaseViewModel<BrowseEventsIntent, BrowseEventsViewState> {

    private var intentsSubject: PublishSubject<BrowseEventsIntent> = PublishSubject.create()
    private val intentsFilter: ObservableTransformer<BrowseEventsIntent, BrowseEventsIntent> = ObservableTransformer {
        it.publish {
            Observable.merge(
                    it.ofType(BrowseEventsIntent.InitialIntent::class.java).take(1),
                    it.filter({ intent -> intent !is BrowseEventsIntent.InitialIntent })
            )
        }
    }
    private val reducer: BiFunction<BrowseEventsViewState, BrowseEventsResult, BrowseEventsViewState> =
        BiFunction { previousState, result ->
            when (result) {
                is BrowseEventsResult.BrowseEvents -> {
                    when {
                        result.taskStatus == TaskStatus.SUCCESS ->
                            BrowseEventsViewState.BrowseEventsSuccess(
                                    result.events?.map {
                                        eventMapper.mapToViewModel(it)
                                    })
                        result.taskStatus == TaskStatus.IN_WORK -> BrowseEventsViewState.InProgress
                        result.taskStatus == TaskStatus.FAILURE -> BrowseEventsViewState.Failed
                        else -> BrowseEventsViewState.Idle
                    }
                }
            }

        }
    private val statesSubject: Observable<BrowseEventsViewState> = compose()

    override fun processIntents(intents: Observable<BrowseEventsIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<BrowseEventsViewState> {
        return statesSubject
    }

    private fun compose(): Observable<BrowseEventsViewState> {
        return intentsSubject
            .compose(intentsFilter)
            .map { this.actionFromIntent(it) }
            .compose(browseProcessor.actionProcessor)
            .scan<BrowseEventsViewState>(BrowseEventsViewState.Idle, reducer)
            .replay(1)
            .autoConnect(0)
    }

    private fun actionFromIntent(intent: BrowseEventsIntent): BrowseEventsAction {

        return when (intent) {
            is BrowseEventsIntent.BrowseEvents ->
                BrowseEventsAction.BrowseEvents(intent.visibleScreenBounds)

            is BrowseEventsIntent.InitialIntent ->
                BrowseEventsAction.BrowseEvents(null)

            else -> throw UnsupportedOperationException("Oops, that looks like an unknown intent: $intent")
        }
    }

}