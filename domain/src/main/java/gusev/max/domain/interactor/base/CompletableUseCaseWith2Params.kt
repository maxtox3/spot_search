package gusev.max.domain.interactor.base

import gusev.max.domain.executor.PostExecutionThread
import gusev.max.domain.executor.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers

/**
 * Created by v on 24/06/2018.
 */
abstract class CompletableUseCaseWith2Params<in Params, in Params2> protected constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    private val subscription = Disposables.empty()

    protected abstract fun buildUseCaseObservable(params: Params?, params2: Params2?): Completable

    fun execute(params: Params?, params2: Params2?): Completable {
        return this.buildUseCaseObservable(params, params2)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
    }

    fun unsubscribe() {
        if (!subscription.isDisposed) {
            subscription.dispose()
        }
    }

}