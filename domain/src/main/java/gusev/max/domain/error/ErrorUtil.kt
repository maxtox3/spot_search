package gusev.max.domain.error

/**
 * Created by v on 12/06/2018.
 */
object ErrorUtil {

    const val ERROR = "Произошла ошибка"

    fun authErrorTransformer(authError: AuthError): String {
        return authError.remoteMessage
    }
}