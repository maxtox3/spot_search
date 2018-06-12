package gusev.max.remote.pojo.auth

import gusev.max.remote.model.UserModel
import gusev.max.remote.pojo.base.BasePojo

/**
 * Created by v on 11/06/2018.
 */
data class AuthResponsePojo(
    var token: String? = null,
    var user: UserModel? = null
) : BasePojo()