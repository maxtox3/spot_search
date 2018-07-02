package gusev.max.spotsearch.mapper

import gusev.max.presentation.model.BaseModel
import gusev.max.spotsearch.model.BaseUIModel

/**
 * Created by v on 18/06/2018.
 */
interface Mapper<P : BaseModel, M : BaseUIModel> {

    fun mapToUIModel(presentationModel: P): M

    fun mapToPresentationModel(uiModel: M): P
}