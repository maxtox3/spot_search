package gusev.max.spotsearch.model

import android.os.Parcel
import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

/**
 * Created by v on 18/06/2018.
 */
data class EventUIModel(
    val id: Long,
    val userId: Long,
    val actionId: Long,
    val name: String,
    val description: String,
    val photoUrl: String,
    val latLng: LatLng,
    val likesCount: Int,
    val dislikesCount: Int,
    override var chosen: Boolean = false
) : BaseUIModel, ClusterItem, Parcelable {

    override fun getPosition(): LatLng {
        return latLng
    }

    override fun getSnippet(): String {
        return description
    }

    override fun getTitle(): String {
        return name
    }

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readLong(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(LatLng::class.java.classLoader),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeLong(userId)
        parcel.writeLong(actionId)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(photoUrl)
        parcel.writeParcelable(latLng, flags)
        parcel.writeInt(likesCount)
        parcel.writeInt(dislikesCount)
        parcel.writeByte(if (chosen) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EventUIModel> {
        override fun createFromParcel(parcel: Parcel): EventUIModel {
            return EventUIModel(parcel)
        }

        override fun newArray(size: Int): Array<EventUIModel?> {
            return arrayOfNulls(size)
        }
    }
}