package gusev.max.presentation.map

import gusev.max.domain.model.Event
import gusev.max.presentation.base.BaseResult
import gusev.max.presentation.base.model.TaskStatus

/**
 * Created by v on 09/06/2018.
 */
sealed class BrowseEventsResult : BaseResult {

    class BrowseEvents(
        val taskStatus: TaskStatus,
        val events: List<Event>? = null
    ) : BrowseEventsResult() {

        companion object {

            internal fun success(events: List<Event>): BrowseEvents {
                return BrowseEvents(
                        TaskStatus.SUCCESS,
                        events
                )
            }

            internal fun failure(): BrowseEvents {
                return BrowseEvents(
                        TaskStatus.FAILURE,
                        null
                )
            }

            internal fun inFlight(): BrowseEvents {
                return BrowseEvents(
                        TaskStatus.IN_WORK
                )
            }
        }
    }
}