package gusev.max.data.entity

/**
 * Created by v on 10/06/2018.
 */
data class EventEntity(
    val id: Long,
    val actionId: Long,
    val userId: Long,
    val name: String,
    val description: String,
    val photoUrl: String,
    val latitude: Double,
    val longitude: Double,
    val likesCount: Int,
    val dislikesCount: Int
) : BaseEntity