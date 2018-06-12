package gusev.max.presentation.auth

import android.arch.lifecycle.ViewModel
import gusev.max.presentation.base.BaseViewModel
import gusev.max.presentation.base.model.TaskStatus
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by v on 11/06/2018.
 */
class AuthViewModel @Inject constructor(
    private val authProcessor: AuthProcessor,
    private val signUpProcessor: SignUpProcessor,
    private val checkAuthProcessor: CheckAuthProcessor
) : ViewModel(), BaseViewModel<AuthIntent, AuthViewState> {

    private var intentsSubject: PublishSubject<AuthIntent> = PublishSubject.create()
    private val intentFilter: ObservableTransformer<AuthIntent, AuthIntent> =
        ObservableTransformer {
            it.filter({ intent -> intent !== AuthIntent.InitialIntent })
        }
    private val reducer: BiFunction<AuthViewState, AuthResult, AuthViewState> =
        BiFunction { previousState, result ->
            when (result) {
                is AuthResult.Login -> {
                    when {
                        result.taskStatus == TaskStatus.SUCCESS -> AuthViewState.LoginSuccess
                        result.taskStatus == TaskStatus.IN_WORK -> AuthViewState.InProgress
                        result.taskStatus == TaskStatus.FAILURE -> AuthViewState.AuthFailed(
                                result.errorMessage ?: ""
                        )
                        else -> AuthViewState.Idle
                    }
                }
                is AuthResult.SignUp -> {
                    when {
                        result.taskStatus == TaskStatus.SUCCESS -> AuthViewState.SignUpSuccess
                        result.taskStatus == TaskStatus.IN_WORK -> AuthViewState.InProgress
                        result.taskStatus == TaskStatus.FAILURE -> AuthViewState.SignUpFailed(
                                result.errorMessage ?: ""
                        )
                        else -> AuthViewState.Idle
                    }
                }
                is AuthResult.CheckAuth -> {
                    when {
                        result.taskStatus == TaskStatus.SUCCESS -> AuthViewState.LoginSuccess
                        result.taskStatus == TaskStatus.FAILURE -> AuthViewState.CheckAuthFailed
                        else -> AuthViewState.Idle
                    }
                }
            }
        }
    private val statesSubject: Observable<AuthViewState> = compose()

    override fun processIntents(intents: Observable<AuthIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<AuthViewState> {
        return statesSubject
    }

    private fun compose(): Observable<AuthViewState> {
        return intentsSubject
            .compose(intentFilter)
            .map { this.actionFromIntent(it) }
            .flatMap { reduceAction(it) }
            .scan<AuthViewState>(AuthViewState.Idle, reducer)
            .replay(1)
            .autoConnect(0)
    }

    private fun reduceAction(action: AuthAction): Observable<AuthResult> {
        return Observable.just(action)
            .switchMap {
                when (action) {
                    is AuthAction.Login ->
                        authProcessor.actionProcessor.apply(Observable.just(it))
                    is AuthAction.SignUp ->
                        signUpProcessor.actionProcessor.apply(Observable.just(it))
                    is AuthAction.CheckAuth ->
                        checkAuthProcessor.actionProcessor.apply(Observable.just(it))
                }
            }
    }

    private fun actionFromIntent(intent: AuthIntent): AuthAction {

        return when (intent) {
            is AuthIntent.InitialIntent -> AuthAction.CheckAuth
            is AuthIntent.CheckAuthIntent -> AuthAction.CheckAuth
            is AuthIntent.LoginIntent -> AuthAction.Login(
                    email = intent.email,
                    password = intent.password
            )
            is AuthIntent.SignUpIntent -> AuthAction.SignUp(
                    name = intent.name,
                    email = intent.email,
                    password = intent.password
            )

            else -> throw UnsupportedOperationException("Oops, that looks like an unknown intent: $intent")
        }
    }
}