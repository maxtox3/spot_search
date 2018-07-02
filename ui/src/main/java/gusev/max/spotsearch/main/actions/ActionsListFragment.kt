package gusev.max.spotsearch.main.actions

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import gusev.max.presentation.main.actions.ActionsIntent
import gusev.max.presentation.main.actions.ActionsViewModel
import gusev.max.presentation.main.actions.ActionsViewState
import gusev.max.spotsearch.R
import gusev.max.spotsearch.base.fragment.BaseLceFragment
import gusev.max.spotsearch.main.MainActivityCallback
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_actions_list.*
import javax.inject.Inject

/**
 * Created by v on 13/06/2018.
 */
class ActionsListFragment :
        BaseLceFragment<ActionsIntent, ActionsViewState, ActionsViewModel, MainActivityCallback>() {

    private val tryAgainPublisher: PublishSubject<ActionsIntent.TryAgain> = PublishSubject.create()

    @Inject
    lateinit var actionsAdapter: ActionsAdapter

    override fun intents(): Observable<ActionsIntent> {
        return Observable.merge(
                Observable.just(ActionsIntent.InitialIntent),
                Observable.just(ActionsIntent.BrowseActionsIntent),
                tryAgainPublisher
        )
    }

    override fun render(state: ActionsViewState) {
        when {
            state === ActionsViewState.InProgress -> setupScreenForLoading()
            state === ActionsViewState.Failed -> setupScreenForError(resources.getString(R.string.general_error))
//            state is ActionsViewState.BrowseActionsSuccess -> setupForSuccess(state.comments)
        }
    }

//    private fun setupForSuccess(comments: List<ActionModel>?) {
//        getLoadingView().visibility = View.GONE
//        if (comments == null || comments.isEmpty()) {
//            setupScreenForError(resources.getString(R.string.error_empty))
//        } else {
//            actionsAdapter.setData(comments)
//        }
//    }


    override fun initActivityCallback(context: Context?) {
        activityCallback = context as MainActivityCallback
    }

    override fun getContainerId(): Int {
        return R.layout.fragment_actions_list
    }

    override fun setupWidgets() {
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = actionsAdapter
    }

    override fun getViewModel(): ActionsViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get(ActionsViewModel::class.java)
    }

    override fun getLoadingView(): View {
        return progress_bar
    }

}