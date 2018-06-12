package gusev.max.domain.interactor.base

import gusev.max.domain.executor.PostExecutionThread
import gusev.max.domain.executor.ThreadExecutor
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

/**
 * Created by v on 10/06/2018.
 */
abstract class FlowableUseCase<T, in Params> constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    protected abstract fun buildUseCaseObservable(params: Params? = null): Flowable<T>

    open fun execute(params: Params? = null): Flowable<T> {
        return this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
    }

}