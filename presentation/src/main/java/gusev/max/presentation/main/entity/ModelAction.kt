package gusev.max.presentation.main.entity

import gusev.max.domain.model.BaseDomainModel
import gusev.max.presentation.base.BaseAction

/**
 * Created by v on 23/06/2018.
 */
sealed class ModelAction<M : BaseDomainModel> : BaseAction {

    class BrowseModels<M : BaseDomainModel> : ModelAction<M>()

    class CreateModel<M : BaseDomainModel> : ModelAction<M>()

    class UpdateModel<M : BaseDomainModel> : ModelAction<M>()

    class DeleteModel<M : BaseDomainModel> : ModelAction<M>()
}