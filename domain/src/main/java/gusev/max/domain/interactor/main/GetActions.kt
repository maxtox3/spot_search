package gusev.max.domain.interactor.main

import gusev.max.domain.executor.PostExecutionThread
import gusev.max.domain.executor.ThreadExecutor
import gusev.max.domain.interactor.base.FlowableUseCase
import gusev.max.domain.model.Action
import gusev.max.domain.repository.ActionsRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by v on 13/06/2018.
 */
class GetActions @Inject constructor(
    private val repository: ActionsRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : FlowableUseCase<List<Action>, Void?>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Void?): Flowable<List<Action>> {
        return repository.getModels()
    }

}