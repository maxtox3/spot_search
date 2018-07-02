package gusev.max.presentation.main.event_full_info

import android.arch.lifecycle.ViewModel
import gusev.max.presentation.base.BaseViewModel
import gusev.max.presentation.base.model.TaskStatus
import gusev.max.presentation.mapper.CommentMapper
import gusev.max.presentation.mapper.EventMapper
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by v on 27/06/2018.
 */
class EventFullInfoViewModel @Inject constructor(
    private val browseCommentsProcessor: BrowseCommentsProcessor,
    private val browseEventProcessor: BrowseEventProcessor,
    private val eventMapper: EventMapper,
    private val commentsMapper: CommentMapper
) : ViewModel(), BaseViewModel<EventFullInfoIntent, EventFullInfoViewState> {

    private var intentsSubject: PublishSubject<EventFullInfoIntent> = PublishSubject.create()
    private val intentsFilter: ObservableTransformer<EventFullInfoIntent, EventFullInfoIntent> = ObservableTransformer {
        it.filter({ intent -> intent !== EventFullInfoIntent.InitialIntent })
    }
    private val reducer: BiFunction<EventFullInfoViewState, EventFullInfoResult, EventFullInfoViewState> =
        BiFunction { previousState, result ->
            when (result) {
                is EventFullInfoResult.BrowseEvent -> {
                    when {
                        result.taskStatus == TaskStatus.SUCCESS ->
                            EventFullInfoViewState.EventLoaded(eventMapper.mapToViewModel(result.event!!))
                        result.taskStatus == TaskStatus.IN_WORK -> EventFullInfoViewState.InProgress
                        result.taskStatus == TaskStatus.FAILURE -> EventFullInfoViewState.LoadEventFailed
                        else -> EventFullInfoViewState.Idle
                    }
                }
                is EventFullInfoResult.BrowseComments -> {
                    when {
                        result.taskStatus == TaskStatus.SUCCESS -> EventFullInfoViewState.CommentsLoaded(
                                result.comments?.map {
                                    commentsMapper.mapToViewModel(it)
                                } ?: arrayListOf()
                        )
                        result.taskStatus == TaskStatus.IN_WORK -> EventFullInfoViewState.InProgress
                        result.taskStatus == TaskStatus.FAILURE -> EventFullInfoViewState.LoadCommentsFailed
                        else -> EventFullInfoViewState.Idle
                    }
                }
            }

        }
    private val statesSubject: Observable<EventFullInfoViewState> = compose()

    override fun processIntents(intents: Observable<EventFullInfoIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<EventFullInfoViewState> {
        return statesSubject
    }

    private fun compose(): Observable<EventFullInfoViewState> {
        return intentsSubject
            .compose(intentsFilter)
            .map { this.actionFromIntent(it) }
            .flatMap { reduceAction(it) }
            .scan<EventFullInfoViewState>(EventFullInfoViewState.Idle, reducer)
            .replay(1)
            .autoConnect(0)
    }

    private fun reduceAction(action: EventFullInfoAction): Observable<EventFullInfoResult> {
        return Observable.just(action)
            .switchMap {
                when (action) {

                    is EventFullInfoAction.BrowseEvent ->
                        browseEventProcessor.actionProcessor.apply(Observable.just(action))

                    is EventFullInfoAction.BrowseComments ->
                        browseCommentsProcessor.actionProcessor.apply(Observable.just(action))
                }
            }
    }

    private fun actionFromIntent(intent: EventFullInfoIntent): EventFullInfoAction {

        return when (intent) {

            is EventFullInfoIntent.InitialIntent -> EventFullInfoAction.BrowseEvent(null)

            is EventFullInfoIntent.BrowseEvent -> EventFullInfoAction.BrowseEvent(intent.eventId)

            is EventFullInfoIntent.BrowseComments -> EventFullInfoAction.BrowseComments(intent.eventId)

            else -> throw UnsupportedOperationException("Oops, that looks like an unknown intent: $intent")

        }
    }
}