package gusev.max.domain.interactor.auth

import gusev.max.domain.executor.PostExecutionThread
import gusev.max.domain.executor.ThreadExecutor
import gusev.max.domain.interactor.base.CompletableUseCase
import gusev.max.domain.model.User
import gusev.max.domain.repository.AuthRepository
import gusev.max.domain.repository.UserRepository
import io.reactivex.Completable
import java.util.*
import javax.inject.Inject

/**
 * Created by v on 10/06/2018.
 */
class AuthorizeUser @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<User>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: User?): Completable {
        return authRepository.authorizeUser(params!!)
            .flatMapCompletable {
                userRepository.saveModels(Collections.singletonList(it))
            }
    }

}