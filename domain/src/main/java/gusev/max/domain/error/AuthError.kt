package gusev.max.domain.error

/**
 * Created by v on 11/06/2018.
 */
data class AuthError(val remoteMessage: String) : Throwable()