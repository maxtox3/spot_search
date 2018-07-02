package gusev.max.presentation.main.map

import gusev.max.domain.model.Action
import gusev.max.domain.model.Event
import gusev.max.domain.model.map.LatLngModel
import gusev.max.presentation.base.BaseResult
import gusev.max.presentation.base.model.TaskStatus

/**
 * Created by v on 19/06/2018.
 */
sealed class MapResult : BaseResult {

    class BrowseEvents(
        val taskStatus: TaskStatus,
        val events: List<Event>? = null
    ) : MapResult() {

        companion object {

            internal fun success(events: List<Event>): BrowseEvents {
                return BrowseEvents(TaskStatus.SUCCESS, events)
            }

            internal fun failure(): BrowseEvents {
                return BrowseEvents(TaskStatus.FAILURE, null)
            }

            internal fun inFlight(): BrowseEvents {
                return BrowseEvents(TaskStatus.IN_WORK)
            }
        }
    }

    class BrowseActions(
        val taskStatus: TaskStatus,
        val actions: List<Action>? = null
    ) : MapResult() {

        companion object {

            internal fun success(actions: List<Action>): BrowseActions {
                return BrowseActions(TaskStatus.SUCCESS, actions)
            }

            internal fun failure(): BrowseActions {
                return BrowseActions(TaskStatus.FAILURE, null)
            }

            internal fun inFlight(): BrowseActions {
                return BrowseActions(TaskStatus.IN_WORK)
            }
        }
    }

    class CreateEvent(
        val taskStatus: TaskStatus
    ) : MapResult() {

        companion object {

            internal fun success(): CreateEvent {
                return CreateEvent(TaskStatus.SUCCESS)
            }

            internal fun failure(): CreateEvent {
                return CreateEvent(TaskStatus.FAILURE)
            }

            internal fun inFlight(): CreateEvent {
                return CreateEvent(TaskStatus.IN_WORK)
            }
        }
    }

    class ShowDialog(val latLngModel: LatLngModel) : MapResult()
}