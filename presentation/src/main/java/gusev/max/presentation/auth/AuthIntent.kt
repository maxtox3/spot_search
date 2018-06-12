package gusev.max.presentation.auth

import gusev.max.presentation.base.BaseIntent

/**
 * Created by v on 11/06/2018.
 */
sealed class AuthIntent : BaseIntent {

    object InitialIntent : AuthIntent()

    object CheckAuthIntent : AuthIntent()

    class LoginIntent(val email: String, val password: String) : AuthIntent()

    class SignUpIntent(val name: String, val email: String, val password: String) : AuthIntent()
}