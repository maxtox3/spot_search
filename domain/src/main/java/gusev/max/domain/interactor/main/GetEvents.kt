package gusev.max.domain.interactor.main

import gusev.max.domain.executor.PostExecutionThread
import gusev.max.domain.executor.ThreadExecutor
import gusev.max.domain.interactor.base.FlowableUseCase
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
) : FlowableUseCase<List<Event>, LatLngBoundsModel?>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: LatLngBoundsModel?): Flowable<List<Event>> {
        return eventsRepository.getModelsByBounds(params!!)
    }
}