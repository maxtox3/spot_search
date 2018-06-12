package gusev.max.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by v on 10/06/2018.
 */
data class EventModel(
    val id: Long,
    @SerializedName("user_id")
    val userId: Long,
    val name: String,
    val description: String,
    @SerializedName("photo_url")
    val photoUrl: String?,
    val lat: Double,
    val lng: Double,
    @SerializedName("likes")
    val likesCount: Int,
    @SerializedName("dislikes")
    val dislikesCount: Int,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
) : BaseRemoteModel