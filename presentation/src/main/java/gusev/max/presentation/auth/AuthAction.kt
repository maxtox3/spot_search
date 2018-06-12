package gusev.max.presentation.auth

import gusev.max.presentation.base.BaseAction

/**
 * Created by v on 11/06/2018.
 */
sealed class AuthAction : BaseAction {

    object CheckAuth : AuthAction()

    class Login(val email: String, val password: String) : AuthAction()

    class SignUp(val name: String, val email: String, val password: String) : AuthAction()
}