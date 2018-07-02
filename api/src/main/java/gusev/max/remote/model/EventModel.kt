package gusev.max.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by v on 10/06/2018.
 */
data class EventModel(
    val id: Long? = null,
    @SerializedName("action_id")
    val actionId: Long,
    @SerializedName("user_id")
    val userId: Long? = null,
    val name: String,
    val description: String,
    @SerializedName("photo_url")
    val photoUrl: String? = null,
    val lat: Double,
    val lng: Double,
    @SerializedName("likes")
    val likesCount: Int? = 0,
    @SerializedName("dislikes")
    val dislikesCount: Int? = 0,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null
) : BaseRemoteModel