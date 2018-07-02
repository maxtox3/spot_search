package gusev.max.domain.interactor.main.event

import gusev.max.domain.executor.PostExecutionThread
import gusev.max.domain.executor.ThreadExecutor
import gusev.max.domain.interactor.base.FlowableUseCase
import gusev.max.domain.model.Event
import gusev.max.domain.repository.EventsRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by v on 27/06/2018.
 */
class GetEvent @Inject constructor(
    private val eventsRepository: EventsRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : FlowableUseCase<Event, Long?>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Long?): Flowable<Event> {
        return eventsRepository.getModelById(params!!)
    }
}