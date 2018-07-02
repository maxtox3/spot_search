package gusev.max.presentation.main.actions

import android.arch.lifecycle.ViewModel
import gusev.max.presentation.base.BaseViewModel
import gusev.max.presentation.base.model.TaskStatus
import gusev.max.presentation.mapper.ActionMapper
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by v on 13/06/2018.
 */

open class ActionsViewModel @Inject constructor(
    private val browseProcessor: BrowseActionsProcessor,
    private val mapper: ActionMapper
) : ViewModel(), BaseViewModel<ActionsIntent, ActionsViewState> {

    private var intentsSubject: PublishSubject<ActionsIntent> = PublishSubject.create()
    private val intentsFilter: ObservableTransformer<ActionsIntent, ActionsIntent> = ObservableTransformer {
        it.filter({ intent -> intent !== ActionsIntent.InitialIntent })
    }

    private val reducer: BiFunction<ActionsViewState, ActionsResult, ActionsViewState> =
        BiFunction { previousState, result ->
            when (result) {
                is ActionsResult.BrowseActions -> {
                    when {
                        result.taskStatus == TaskStatus.SUCCESS ->
                            ActionsViewState.BrowseActionsSuccess(
                                    result.actions?.map {
                                        mapper.mapToViewModel(it)
                                    } ?: arrayListOf())
                        result.taskStatus == TaskStatus.IN_WORK -> ActionsViewState.InProgress
                        result.taskStatus == TaskStatus.FAILURE -> ActionsViewState.Failed
                        else -> ActionsViewState.Idle
                    }
                }
            }

        }
    private val statesSubject: Observable<ActionsViewState> = compose()

    override fun processIntents(intents: Observable<ActionsIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<ActionsViewState> {
        return statesSubject
    }

    private fun compose(): Observable<ActionsViewState> {
        return intentsSubject
            .compose(intentsFilter)
            .map { this.actionsFromIntent(it) }
            .compose(browseProcessor.actionProcessor)
            .scan<ActionsViewState>(ActionsViewState.Idle, reducer)
            .replay(1)
            .autoConnect(0)
    }

    private fun actionsFromIntent(intent: ActionsIntent): ActionsAction {

        return when (intent) {
            is ActionsIntent.BrowseActionsIntent -> ActionsAction.BrowseActions
            is ActionsIntent.InitialIntent -> ActionsAction.BrowseActions
            is ActionsIntent.TryAgain -> ActionsAction.BrowseActions

            else -> throw UnsupportedOperationException("Oops, that looks like an unknown intent: $intent")
        }
    }
}