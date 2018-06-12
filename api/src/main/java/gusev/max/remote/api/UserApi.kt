package gusev.max.remote.api

import gusev.max.remote.pojo.user.UserPojo
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by v on 11/06/2018.
 */
interface UserApi {

    companion object {
        const val ID_PATH = "id"
        const val URL_USER_GET = "user/{$ID_PATH}"
    }

    @GET(URL_USER_GET)
    fun getUser(@Path(ID_PATH) id: Long) : Flowable<UserPojo>
}