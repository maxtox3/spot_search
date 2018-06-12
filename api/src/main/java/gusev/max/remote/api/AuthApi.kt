package gusev.max.remote.api

import gusev.max.remote.pojo.auth.AuthResponsePojo
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by v on 11/06/2018.
 */
interface AuthApi {

    companion object {
        const val URL_LOGIN = "auth/login"
        const val URL_SIGNUP = "auth/signup"
        const val URL_LOGOUT = "auth/login"
        const val URL_ME = "me"
    }

    @FormUrlEncoded
    @POST(URL_LOGIN)
    fun login(@Field("email") email: String, @Field("password") password: String): Flowable<AuthResponsePojo>

    @FormUrlEncoded
    @POST(URL_SIGNUP)
    fun signUp(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Flowable<AuthResponsePojo>

    @GET(URL_ME)
    fun me(): Single<AuthResponsePojo>


}