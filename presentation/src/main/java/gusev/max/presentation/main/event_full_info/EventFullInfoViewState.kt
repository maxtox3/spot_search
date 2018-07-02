package gusev.max.presentation.main.event_full_info

import gusev.max.presentation.base.BaseViewState
import gusev.max.presentation.model.CommentaryModel
import gusev.max.presentation.model.EventModel

/**
 * Created by v on 27/06/2018.
 */
sealed class EventFullInfoViewState(
    val event: EventModel? = null,
    val comments: List<CommentaryModel>? = null
) : BaseViewState {

    object InProgress : EventFullInfoViewState()

    object LoadEventFailed : EventFullInfoViewState()

    object LoadCommentsFailed : EventFullInfoViewState()

    class EventLoaded(val eventModel: EventModel) : EventFullInfoViewState(eventModel)

    class CommentsLoaded(val commentList: List<CommentaryModel>) :
            EventFullInfoViewState(comments = commentList)

    object Idle : EventFullInfoViewState()
}