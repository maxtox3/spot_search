package gusev.max.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by v on 28/06/2018.
 */
data class CommentModel(
    val id: Long,
    @SerializedName("event_id")
    val eventId: Long,
    @SerializedName("user_id")
    val userId: Long,
    @SerializedName("avatar")
    val avatar: String? = null,
    val text: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null
) : BaseRemoteModel