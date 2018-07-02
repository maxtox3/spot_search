package gusev.max.remote.api

import gusev.max.remote.model.EventModel
import gusev.max.remote.pojo.event.EventListPojo
import gusev.max.remote.pojo.event.EventPojo
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by v on 11/06/2018.
 */
interface EventApi {

    companion object {
        const val URL_EVENTS_GET_BY_LAT_LNG = "events"
        const val URL_POST_EVENT = "event"
    }

    @GET(URL_EVENTS_GET_BY_LAT_LNG)
    fun getEventsByLatLngBounds(
        @Query("north") north: Double,
        @Query("east") east: Double,
        @Query("south") south: Double,
        @Query("west") west: Double
    ): Flowable<EventListPojo>

    @GET(URL_EVENTS_GET_BY_LAT_LNG)
    fun getEventsByLatLngBoundsAndActionId(
        @Query("action_id") actionId: Long,
        @Query("north") north: Double,
        @Query("east") east: Double,
        @Query("south") south: Double,
        @Query("west") west: Double
    ): Flowable<EventListPojo>

    @POST(URL_POST_EVENT)
    fun saveEvent(@Body event: EventModel): Completable

    fun getEventById(id: Long): Flowable<EventPojo>
}