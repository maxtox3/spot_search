package gusev.max.remote.api

import gusev.max.remote.pojo.event.CommentsListPojo
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by v on 28/06/2018.
 */
interface CommentApi {

    companion object {
        const val URL_COMMENTS_BY_EVENT_ID = "comments/{id}"
    }

    @GET(URL_COMMENTS_BY_EVENT_ID)
    fun getCommentsByEventId(@Path("id") eventId: Long): Flowable<CommentsListPojo>
}