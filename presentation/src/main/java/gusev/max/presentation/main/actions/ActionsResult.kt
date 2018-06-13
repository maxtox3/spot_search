package gusev.max.presentation.main.actions

import gusev.max.domain.model.Action
import gusev.max.presentation.base.BaseResult
import gusev.max.presentation.base.model.TaskStatus

/**
 * Created by v on 13/06/2018.
 */
sealed class ActionsResult : BaseResult {

    class BrowseActions(
        val taskStatus: TaskStatus,
        val actions: List<Action>? = null
    ) : ActionsResult() {

        companion object {

            internal fun success(events: List<Action>): BrowseActions {
                return BrowseActions(
                        TaskStatus.SUCCESS,
                        events
                )
            }

            internal fun failure(): BrowseActions {
                return BrowseActions(
                        TaskStatus.FAILURE,
                        null
                )
            }

            internal fun inFlight(): BrowseActions {
                return BrowseActions(
                        TaskStatus.IN_WORK
                )
            }
        }
    }

}