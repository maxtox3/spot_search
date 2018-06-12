package gusev.max.presentation.auth

import gusev.max.domain.interactor.auth.AuthorizeUser
import gusev.max.presentation.mapper.UserMapper
import io.reactivex.ObservableTransformer
import javax.inject.Inject

/**
 * Created by v on 11/06/2018.
 */
class AuthProcessor @Inject constructor(
    private val login: AuthorizeUser,
    private val userMapper: UserMapper
) : BaseAuthProcessor() {
    private val processor: ObservableTransformer<AuthAction.Login, AuthResult> = ObservableTransformer {
        it.map {
            userMapper.mapToLoginModel(it.email, it.password)
        }.map {
            userMapper.mapToDomainModel(it)
        }.switchMap {
            login.execute(it)
                .toSingleDefault(AuthResult.Login.success())
                .onErrorReturn {
                    AuthResult.Login.failure(catchAuthError(it))
                }
                .toObservable()
                .startWith(AuthResult.Login.inFlight())
        }
    }

    var actionProcessor: ObservableTransformer<AuthAction, AuthResult>

    init {
        this.actionProcessor = ObservableTransformer {
            it.publish {
                it.ofType(AuthAction.Login::class.java)
                    .compose(processor)
            }
        }
    }

}