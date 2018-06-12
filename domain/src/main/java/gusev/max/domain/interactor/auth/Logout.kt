package gusev.max.domain.interactor.auth

import gusev.max.domain.executor.PostExecutionThread
import gusev.max.domain.executor.ThreadExecutor
import gusev.max.domain.interactor.base.CompletableUseCase
import gusev.max.domain.model.User
import gusev.max.domain.repository.AuthRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by v on 10/06/2018.
 */
class Logout @Inject constructor(
    private val authRepository: AuthRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<User>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: User?): Completable {
        return authRepository.logout()
    }
}