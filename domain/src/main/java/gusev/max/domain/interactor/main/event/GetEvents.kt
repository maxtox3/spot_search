package gusev.max.domain.interactor.main.event

import gusev.max.domain.executor.PostExecutionThread
import gusev.max.domain.executor.ThreadExecutor
import gusev.max.domain.interactor.base.FlowableUseCaseWith2Params
import gusev.max.domain.model.Event
import gusev.max.domain.model.map.LatLngBoundsModel
import gusev.max.domain.repository.EventsRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by v on 10/06/2018.
 */
class GetEvents @Inject constructor(
    private val eventsRepository: EventsRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : FlowableUseCaseWith2Params<List<Event>, LatLngBoundsModel?, Long?>(
        threadExecutor,
        postExecutionThread
) {

    override fun buildUseCaseObservable(
        params: LatLngBoundsModel?,
        params2: Long?
    ): Flowable<List<Event>> {
        return eventsRepository.getModelsByBoundsAndActionId(params!!, params2!!)
    }
}