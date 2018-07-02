package gusev.max.presentation.model

import gusev.max.domain.model.map.LatLngModel

/**
 * Created by v on 10/06/2018.
 */
data class EventModel(
    val id: Long,
    val userId: Long,
    val actionId: Long,
    val name: String,
    val description: String,
    val photoUrl: String,
    val latLng: LatLngModel,
    val likesCount: Int,
    val dislikesCount: Int
) : BaseModel