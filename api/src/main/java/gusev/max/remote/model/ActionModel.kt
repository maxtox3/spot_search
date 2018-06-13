package gusev.max.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by v on 13/06/2018.
 */
data class ActionModel(
    val id: Long,
    val name: String? = null,
    val description: String? = null,
    @SerializedName("primary_color")
    val primaryColor: String? = null,
    @SerializedName("second_color")
    val secondaryColor: String? = null
) : BaseRemoteModel