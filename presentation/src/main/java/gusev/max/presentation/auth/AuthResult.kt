package gusev.max.presentation.auth

import gusev.max.presentation.base.BaseResult
import gusev.max.presentation.base.model.TaskStatus

/**
 * Created by v on 11/06/2018.
 */
sealed class AuthResult : BaseResult {

    class Login(
        val taskStatus: TaskStatus,
        val errorMessage: String? = null
    ) : AuthResult() {

        companion object {
            internal fun success(): Login {
                return Login(TaskStatus.SUCCESS)
            }

            internal fun failure(errorMessage: String?): Login {
                return Login(TaskStatus.FAILURE, errorMessage)
            }

            internal fun inFlight(): Login {
                return Login(TaskStatus.IN_WORK)
            }
        }
    }

    class SignUp(
        val taskStatus: TaskStatus,
        val errorMessage: String? = null
    ) : AuthResult() {
        companion object {
            internal fun success(): SignUp {
                return SignUp(TaskStatus.SUCCESS)
            }

            internal fun failure(errorMessage: String?): SignUp {
                return SignUp(TaskStatus.FAILURE, errorMessage)
            }

            internal fun inFlight(): SignUp {
                return SignUp(TaskStatus.IN_WORK)
            }
        }
    }

    class CheckAuth(
        val taskStatus: TaskStatus
    ) : AuthResult() {

        companion object {
            internal fun success(): CheckAuth {
                return CheckAuth(TaskStatus.SUCCESS)
            }

            internal fun failure(): CheckAuth {
                return CheckAuth(TaskStatus.FAILURE)
            }

            internal fun inFlight(): CheckAuth {
                return CheckAuth(TaskStatus.IN_WORK)
            }
        }
    }
}