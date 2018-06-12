package gusev.max.spotsearch.utils

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import gusev.max.domain.model.map.LatLngBoundsModel
import gusev.max.domain.model.map.LatLngModel

/**
 * Created by v on 10/06/2018.
 */
object MapUtils {

    internal fun transformGoogleBoundToModel(bounds: LatLngBounds): LatLngBoundsModel {
        return LatLngBoundsModel(
                transformGoogleLatLngToModel(bounds.northeast),
                transformGoogleLatLngToModel(bounds.southwest)
        )
    }

    internal fun transformGoogleLatLngToModel(latLng: LatLng): LatLngModel {
        return LatLngModel(
                latLng.latitude,
                latLng.longitude
        )
    }

}