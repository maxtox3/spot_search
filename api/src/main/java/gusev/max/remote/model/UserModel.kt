package gusev.max.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by v on 11/06/2018.
 */
data class UserModel(
    var token: String? = null,
    val id: Long,
    val name: String,
    val email: String,
    val avatar: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null
) : BaseRemoteModel