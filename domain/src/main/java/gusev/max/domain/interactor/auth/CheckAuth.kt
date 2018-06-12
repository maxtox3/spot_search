package gusev.max.domain.interactor.auth

import gusev.max.domain.executor.PostExecutionThread
import gusev.max.domain.executor.ThreadExecutor
import gusev.max.domain.interactor.base.CompletableUseCase
import gusev.max.domain.repository.AuthRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by v on 11/06/2018.
 */
class CheckAuth @Inject constructor(
    private val authRepository: AuthRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<Void?>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Void?): Completable {
        return authRepository.checkAuth()
    }


}