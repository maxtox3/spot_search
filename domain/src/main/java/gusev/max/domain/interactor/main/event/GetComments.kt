package gusev.max.domain.interactor.main.event

import gusev.max.domain.executor.PostExecutionThread
import gusev.max.domain.executor.ThreadExecutor
import gusev.max.domain.interactor.base.FlowableUseCase
import gusev.max.domain.model.Commentary
import gusev.max.domain.repository.CommentsRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by v on 27/06/2018.
 */
class GetComments @Inject constructor(
    private val eventsRepository: CommentsRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : FlowableUseCase<List<Commentary>, Long?>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Long?): Flowable<List<Commentary>> {
        return eventsRepository.getCommentsByEventId(params!!)
    }
}