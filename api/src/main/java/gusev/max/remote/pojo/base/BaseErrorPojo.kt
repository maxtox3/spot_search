package gusev.max.remote.pojo.base

/**
 * Created by v on 11/06/2018.
 */
data class BaseErrorPojo(
    val message: String? = null,
    val errors: Map<String, String>? = null
)