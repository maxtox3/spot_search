package gusev.max.domain.interactor.main.event

import gusev.max.domain.executor.PostExecutionThread
import gusev.max.domain.executor.ThreadExecutor
import gusev.max.domain.interactor.base.CompletableUseCase
import gusev.max.domain.model.Event
import gusev.max.domain.repository.EventsRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by v on 15/06/2018.
 */
class CreateEvent @Inject constructor(
    private val eventsRepository: EventsRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<Event>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Event?): Completable {
        return eventsRepository.saveModel(params!!)
    }


}