package gusev.max.remote.api

import gusev.max.remote.pojo.action.ActionListPojo
import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * Created by v on 13/06/2018.
 */
interface ActionApi {

    companion object {
        const val URL_GET_ALL = "actions"
    }

    @GET(URL_GET_ALL)
    fun getActions(): Flowable<ActionListPojo>
}