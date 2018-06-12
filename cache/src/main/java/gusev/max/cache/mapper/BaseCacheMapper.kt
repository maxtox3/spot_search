package gusev.max.cache.mapper

import gusev.max.cache.model.BaseCachedModel
import gusev.max.data.entity.BaseEntity

/**
 * Created by v on 10/06/2018.
 */
interface BaseCacheMapper<C: BaseCachedModel, E: BaseEntity> {

    fun mapFromCached(type: C): E

    fun mapToCached(type: E): C

}