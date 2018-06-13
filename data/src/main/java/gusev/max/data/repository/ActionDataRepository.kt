package gusev.max.data.repository

import gusev.max.data.entity.ActionEntity
import gusev.max.data.mapper.ActionMapper
import gusev.max.data.source.action.ActionCache
import gusev.max.data.source.action.ActionDataStore
import gusev.max.data.sourcefactory.ActionDataStoreFactory
import gusev.max.domain.model.Action
import gusev.max.domain.repository.ActionsRepository
import javax.inject.Inject

/**
 * Created by v on 13/06/2018.
 */
class ActionDataRepository @Inject constructor(
    dataStoreFactory: ActionDataStoreFactory,
    entityMapper: ActionMapper
) : BaseDataRepository<ActionEntity, Action, ActionDataStore, ActionCache>(
        dataStoreFactory,
        entityMapper
), ActionsRepository
