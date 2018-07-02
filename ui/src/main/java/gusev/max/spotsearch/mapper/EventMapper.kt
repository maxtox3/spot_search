package gusev.max.spotsearch.mapper

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import gusev.max.domain.model.map.LatLngBoundsModel
import gusev.max.domain.model.map.LatLngModel
import gusev.max.presentation.model.EventModel
import gusev.max.spotsearch.model.EventUIModel
import javax.inject.Inject

/**
 * Created by v on 18/06/2018.
 */
class EventMapper @Inject constructor() : Mapper<EventModel, EventUIModel> {

    override fun mapToUIModel(presentationModel: EventModel): EventUIModel {
        return EventUIModel(
                id = presentationModel.id,
                userId = presentationModel.userId,
                actionId = presentationModel.actionId,
                name = presentationModel.name,
                description = presentationModel.description,
                photoUrl = presentationModel.photoUrl,
                latLng = transformModelLatLngToGoogle(presentationModel.latLng),
                likesCount = presentationModel.likesCount,
                dislikesCount = presentationModel.dislikesCount
        )
    }

    override fun mapToPresentationModel(uiModel: EventUIModel): EventModel {
        return EventModel(
                id = uiModel.id,
                userId = uiModel.userId,
                actionId = uiModel.actionId,
                name = uiModel.name,
                description = uiModel.description,
                photoUrl = uiModel.photoUrl,
                latLng = transformGoogleLatLngToModel(uiModel.latLng),
                likesCount = uiModel.likesCount,
                dislikesCount = uiModel.dislikesCount
        )
    }

    fun mapToCreateModel(
        actionId: Long,
        name: String,
        description: String,
        latLng: LatLng
    ): EventUIModel {
        return EventUIModel(
                id = -1,
                userId = -1,
                actionId = actionId,
                name = name,
                description = description,
                photoUrl = "",
                latLng = latLng,
                likesCount = 0,
                dislikesCount = 0
        )
    }

    fun transformGoogleBoundsToModel(bounds: LatLngBounds): LatLngBoundsModel {
        return LatLngBoundsModel(
                transformGoogleLatLngToModel(bounds.northeast),
                transformGoogleLatLngToModel(bounds.southwest)
        )
    }

    fun transformGoogleLatLngToModel(latLng: LatLng): LatLngModel {
        return LatLngModel(
                latLng.latitude,
                latLng.longitude
        )
    }

    fun transformModelLatLngToGoogle(latLngModel: LatLngModel): LatLng {
        return LatLng(
                latLngModel.latitude,
                latLngModel.longitude
        )
    }

}