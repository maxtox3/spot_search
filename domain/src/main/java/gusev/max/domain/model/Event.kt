package gusev.max.domain.model

import gusev.max.domain.model.map.LatLngModel

/**
 * Created by v on 10/06/2018.
 */
data class Event(
    val id: Long,
    val userId: Long,
    val name: String,
    val description: String,
    val photoUrl: String,
    val latLng: LatLngModel,
    val likesCount: Int,
    val dislikesCount: Int
) : BaseDomainModel