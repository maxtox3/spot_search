package gusev.max.presentation.main.map

import android.arch.lifecycle.ViewModel
import gusev.max.presentation.base.BaseViewModel
import gusev.max.presentation.base.model.TaskStatus
import gusev.max.presentation.mapper.ActionMapper
import gusev.max.presentation.mapper.EventMapper
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by v on 19/06/2018.
 */
open class MapViewModel @Inject constructor(
    private val browseEventsProcessor: BrowseEventsProcessor,
    private val browseActionsProcessor: BrowseActionsProcessor,
    private val createEventProcessor: CreateEventProcessor,
    private val eventMapper: EventMapper,
    private val actionMapper: ActionMapper
) : ViewModel(), BaseViewModel<MapIntent, MapViewState> {

    private var intentsSubject: PublishSubject<MapIntent> = PublishSubject.create()
    private val intentsFilter: ObservableTransformer<MapIntent, MapIntent> = ObservableTransformer {
        it.filter({ intent -> intent !== MapIntent.InitialIntent })
    }
    private val reducer: BiFunction<MapViewState, MapResult, MapViewState> =
        BiFunction { previousState, result ->
            when (result) {
                is MapResult.BrowseEvents -> {
                    when {
                        result.taskStatus == TaskStatus.SUCCESS ->
                            MapViewState.EventsLoaded(
                                    result.events?.map {
                                        eventMapper.mapToViewModel(it)
                                    } ?: arrayListOf()
                            )
                        result.taskStatus == TaskStatus.IN_WORK -> MapViewState.InProgress
                        result.taskStatus == TaskStatus.FAILURE -> MapViewState.LoadEventsFailed
                        else -> MapViewState.Idle
                    }
                }
                is MapResult.BrowseActions -> {
                    when {
                        result.taskStatus == TaskStatus.SUCCESS ->
                            MapViewState.ActionsLoaded(
                                    result.actions?.map {
                                        actionMapper.mapToViewModel(it)
                                    } ?: arrayListOf()
                            )
                        result.taskStatus == TaskStatus.IN_WORK -> MapViewState.InProgress
                        result.taskStatus == TaskStatus.FAILURE -> MapViewState.LoadActionsFailed
                        else -> MapViewState.Idle
                    }
                }
                is MapResult.CreateEvent -> {
                    when {
                        result.taskStatus == TaskStatus.SUCCESS -> MapViewState.CreateEventSuccess
                        result.taskStatus == TaskStatus.IN_WORK -> MapViewState.InProgress
                        result.taskStatus == TaskStatus.FAILURE -> MapViewState.CreateEventFailed
                        else -> MapViewState.Idle
                    }
                }
                is MapResult.ShowDialog -> MapViewState.CreateEventDialogState(result.latLngModel)
            }

        }
    private val statesSubject: Observable<MapViewState> = compose()

    override fun processIntents(intents: Observable<MapIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<MapViewState> {
        return statesSubject
    }

    private fun compose(): Observable<MapViewState> {
        return intentsSubject
            .compose(intentsFilter)
            .map { this.actionFromIntent(it) }
            .flatMap { reduceAction(it) }
            .scan<MapViewState>(MapViewState.Idle, reducer)
            .replay(1)
            .autoConnect(0)
    }

    private fun reduceAction(action: MapAction): Observable<MapResult> {
        return Observable.just(action)
            .switchMap {
                when (action) {

                    is MapAction.BrowseEvents ->
                        browseEventsProcessor.actionProcessor.apply(Observable.just(action))

                    is MapAction.BrowseActions ->
                        browseActionsProcessor.actionProcessor.apply(Observable.just(action))

                    is MapAction.AddNewMarker ->
                        Observable.just(MapResult.ShowDialog(action.latLng))

                    is MapAction.CreateNewEvent ->
                        createEventProcessor.actionProcessor.apply(Observable.just(action))
                }
            }
    }

    private fun actionFromIntent(intent: MapIntent): MapAction {

        return when (intent) {

            is MapIntent.InitialIntent -> MapAction.BrowseEvents(null)

            is MapIntent.BrowseActions -> MapAction.BrowseActions

            is MapIntent.BrowseEvents ->
                MapAction.BrowseEvents(intent.visibleScreenBounds, intent.actionId)

            is MapIntent.NewMarker -> MapAction.AddNewMarker(intent.latLng)

            is MapIntent.TryLoadEventsAgain -> MapAction.BrowseEvents(intent.visibleScreenBounds)

            is MapIntent.TryLoadActionAgain -> MapAction.BrowseActions

            else -> throw UnsupportedOperationException("Oops, that looks like an unknown intent: $intent")

        }
    }

}