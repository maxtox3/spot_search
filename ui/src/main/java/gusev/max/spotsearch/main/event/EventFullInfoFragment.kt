package gusev.max.spotsearch.main.event

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.View
import gusev.max.presentation.main.event_full_info.EventFullInfoIntent
import gusev.max.presentation.main.event_full_info.EventFullInfoViewModel
import gusev.max.presentation.main.event_full_info.EventFullInfoViewState
import gusev.max.presentation.model.CommentaryModel
import gusev.max.presentation.model.EventModel
import gusev.max.spotsearch.R
import gusev.max.spotsearch.base.fragment.BaseLceFragment
import gusev.max.spotsearch.main.MainActivityCallback
import gusev.max.spotsearch.model.EventUIModel
import gusev.max.spotsearch.utils.Constants
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_event_full_info.*

/**
 * Created by v on 27/06/2018.
 */
class EventFullInfoFragment :
        BaseLceFragment<EventFullInfoIntent, EventFullInfoViewState, EventFullInfoViewModel, MainActivityCallback>() {

    private lateinit var event: EventUIModel

    companion object {
        fun newInstance(event: EventUIModel): EventFullInfoFragment {
            val args = Bundle()
            val fragment = EventFullInfoFragment()
            args.putParcelable(Constants.MODEL, event)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        event = arguments?.getParcelable(Constants.MODEL)!!
        super.onAttach(context)
    }

    override fun intents(): Observable<EventFullInfoIntent> {
        return Observable.merge(
                Observable.just(EventFullInfoIntent.InitialIntent),
                Observable.just(EventFullInfoIntent.BrowseComments(event.id))
        )
    }

    override fun render(state: EventFullInfoViewState) {
        when (state) {
            is EventFullInfoViewState.InProgress -> setupScreenForLoading()
            is EventFullInfoViewState.LoadCommentsFailed -> setupScreenForError(
                    resources.getString(
                            R.string.general_error
                    )
            )
            is EventFullInfoViewState.LoadEventFailed -> setupScreenForError(resources.getString(R.string.general_error))
            is EventFullInfoViewState.CommentsLoaded -> setupScreenForCommentsLoaded(state.commentList)
            is EventFullInfoViewState.EventLoaded -> setupScreenForEventLoaded(state.eventModel)
        }
    }

    private fun setupScreenForEventLoaded(eventModel: EventModel) {

    }

    private fun setupScreenForCommentsLoaded(comments: List<CommentaryModel>) {

    }

    override fun initActivityCallback(context: Context?) {
        activityCallback = context as MainActivityCallback
    }

    override fun getContainerId(): Int {
        return R.layout.fragment_event_full_info
    }

    override fun setupWidgets() {
        toolbar.setNavigationOnClickListener { activityCallback.onBackPressed() }

        toolbar.title = event.name
        full_event_description.text = event.name
        event_likes_count.text = event.likesCount.toString()
        event_dislikes_count.text = event.dislikesCount.toString()
    }

    override fun getViewModel(): EventFullInfoViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get(EventFullInfoViewModel::class.java)
    }

    override fun getLoadingView(): View {
        return View(context)
    }

}