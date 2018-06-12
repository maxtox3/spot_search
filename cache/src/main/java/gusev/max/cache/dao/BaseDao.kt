package gusev.max.cache.dao

import gusev.max.cache.model.BaseCachedModel

interface BaseDao<CACHED_ENTITY : BaseCachedModel> {

    fun getEntities(): List<CACHED_ENTITY>

    fun getEntityById(id: Long): CACHED_ENTITY

    fun saveEntity(cachedEntity: CACHED_ENTITY)

    fun clearEntities()
}