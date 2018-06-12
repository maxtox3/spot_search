package gusev.max.remote.api

import gusev.max.remote.pojo.event.EventListPojo
import gusev.max.remote.pojo.event.EventPojo
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by v on 11/06/2018.
 */
interface EventApi {

    companion object {
        const val URL_EVENTS_GET_BY_LAT_LNG = "/events"
    }

    @GET
    fun getEventsByLatLngBounds(
        @Query("north") north: Double,
        @Query("east") east: Double,
        @Query("south") south: Double,
        @Query("west") west: Double
    ): Flowable<EventListPojo>

    fun getEventById(id: Long): Flowable<EventPojo>
}