package gusev.max.presentation.auth

import gusev.max.domain.interactor.auth.RegisterUser
import gusev.max.presentation.mapper.UserMapper
import io.reactivex.ObservableTransformer
import javax.inject.Inject

/**
 * Created by v on 11/06/2018.
 */
class SignUpProcessor @Inject constructor(
    private val signUp: RegisterUser,
    private val userMapper: UserMapper
) : BaseAuthProcessor() {
    private val processor: ObservableTransformer<AuthAction.SignUp, AuthResult> = ObservableTransformer {
        it.map {
            userMapper.mapToSignUpModel(it.name, it.email, it.password)
        }.map {
            userMapper.mapToDomainModel(it)
        }.switchMap {
            signUp.execute(it)
                .toSingleDefault(AuthResult.SignUp.success())
                .onErrorReturn {
                    AuthResult.SignUp.failure(catchAuthError(it))
                }
                .toObservable()
                .startWith(AuthResult.SignUp.inFlight())
        }
    }
    var actionProcessor: ObservableTransformer<AuthAction, AuthResult>

    init {
        this.actionProcessor = ObservableTransformer {
            it.publish {
                it.ofType(AuthAction.SignUp::class.java)
                    .compose(processor)
            }
        }
    }
}