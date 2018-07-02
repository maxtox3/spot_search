package gusev.max.presentation.main.event_full_info

import gusev.max.domain.model.Commentary
import gusev.max.domain.model.Event
import gusev.max.presentation.base.BaseResult
import gusev.max.presentation.base.model.TaskStatus

/**
 * Created by v on 27/06/2018.
 */
sealed class EventFullInfoResult : BaseResult {

    class BrowseEvent(
        val taskStatus: TaskStatus,
        val event: Event? = null
    ) : EventFullInfoResult() {

        companion object {

            internal fun success(event: Event): BrowseEvent {
                return BrowseEvent(TaskStatus.SUCCESS, event)
            }

            internal fun failure(): BrowseEvent {
                return BrowseEvent(TaskStatus.FAILURE, null)
            }

            internal fun inFlight(): BrowseEvent {
                return BrowseEvent(TaskStatus.IN_WORK)
            }
        }
    }

    class BrowseComments(
        val taskStatus: TaskStatus,
        val comments: List<Commentary>? = null
    ) : EventFullInfoResult() {

        companion object {

            internal fun success(actions: List<Commentary>): BrowseComments {
                return BrowseComments(TaskStatus.SUCCESS, actions)
            }

            internal fun failure(): BrowseComments {
                return BrowseComments(TaskStatus.FAILURE, null)
            }

            internal fun inFlight(): BrowseComments {
                return BrowseComments(TaskStatus.IN_WORK)
            }
        }
    }
}