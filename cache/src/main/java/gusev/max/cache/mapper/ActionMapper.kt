package gusev.max.cache.mapper

import gusev.max.cache.model.CachedAction
import gusev.max.data.entity.ActionEntity
import javax.inject.Inject

/**
 * Created by v on 13/06/2018.
 */
class ActionMapper @Inject constructor() : BaseCacheMapper<CachedAction, ActionEntity> {

    override fun mapFromCached(type: CachedAction): ActionEntity {
        return ActionEntity(
                id = type.id,
                name = type.name,
                description = type.description,
                primaryColor = type.primaryColor,
                secondaryColor = type.secondaryColor
        )
    }

    override fun mapToCached(type: ActionEntity): CachedAction {
        return CachedAction(
                id = type.id,
                name = type.name,
                description = type.description,
                primaryColor = type.primaryColor,
                secondaryColor = type.secondaryColor
        )
    }


}