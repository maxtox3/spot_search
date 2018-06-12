package gusev.max.presentation.auth

import gusev.max.domain.interactor.auth.CheckAuth
import io.reactivex.ObservableTransformer
import javax.inject.Inject

/**
 * Created by v on 11/06/2018.
 */
class CheckAuthProcessor @Inject constructor(
    private val checkAuth: CheckAuth
) {
    var actionProcessor: ObservableTransformer<AuthAction, AuthResult>

    init {
        this.actionProcessor = ObservableTransformer {
            it.publish {
                it.ofType(AuthAction.CheckAuth::class.java)
                    .compose(processor)
            }
        }
    }

    private val processor: ObservableTransformer<AuthAction.CheckAuth, AuthResult> = ObservableTransformer {
        it.switchMap {
            checkAuth.execute(null)
                .toSingleDefault(AuthResult.CheckAuth.success())
                .onErrorReturn { AuthResult.CheckAuth.failure() }
                .toObservable()
                .startWith(AuthResult.CheckAuth.inFlight())
        }
    }
}