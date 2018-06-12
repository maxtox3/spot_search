package gusev.max.presentation.auth

import gusev.max.presentation.base.BaseViewState

/**
 * Created by v on 11/06/2018.
 */
sealed class AuthViewState(
    val inProgress: Boolean = false,
    val loginErrorMessage: String? = null,
    val signUpErrorMessage: String? = null
) : BaseViewState {

    object InProgress : AuthViewState(true)

    class AuthFailed(errorMessage: String) : AuthViewState(false, loginErrorMessage = errorMessage)

    class SignUpFailed(signUpErrorMessage: String) :
            AuthViewState(false, signUpErrorMessage = signUpErrorMessage)

    object CheckAuthFailed : AuthViewState(false)

    object LoginSuccess : AuthViewState()

    object SignUpSuccess : AuthViewState()

    object Idle : AuthViewState()
}