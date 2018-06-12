package gusev.max.presentation.auth

import gusev.max.domain.error.AuthError
import gusev.max.domain.error.ErrorUtil

/**
 * Created by v on 12/06/2018.
 */
abstract class BaseAuthProcessor {

    protected fun catchAuthError(error: Throwable): String {
        var message = ErrorUtil.ERROR
        if (error is AuthError) {
            message = error.remoteMessage
        }
        return message
    }
}