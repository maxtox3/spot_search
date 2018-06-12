package gusev.max.presentation.mapper

import gusev.max.domain.model.BaseDomainModel
import gusev.max.presentation.model.BasePresentationViewModel

/**
 * Created by v on 10/06/2018.
 */
interface Mapper<VIEW_MODEL : BasePresentationViewModel, DOMAIN_MODEL : BaseDomainModel> {

    fun mapToViewModel(entity: DOMAIN_MODEL): VIEW_MODEL

    fun mapToDomainModel(model: VIEW_MODEL): DOMAIN_MODEL
}