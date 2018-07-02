package gusev.max.domain.interactor.base

import gusev.max.domain.executor.PostExecutionThread
import gusev.max.domain.executor.ThreadExecutor
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

/**
 * Created by v on 24/06/2018.
 */
abstract class FlowableUseCaseWith2Params<T, in Params, in Params2> constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    protected abstract fun buildUseCaseObservable(
        params: Params? = null,
        params2: Params2? = null
    ): Flowable<T>

    open fun execute(params: Params? = null, params2: Params2? = null): Flowable<T> {
        return this.buildUseCaseObservable(params, params2)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
    }

}