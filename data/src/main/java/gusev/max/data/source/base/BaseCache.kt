package gusev.max.data.source.base

import gusev.max.data.entity.BaseEntity

interface BaseCache<ENTITY_TYPE : BaseEntity> : BaseDataStore<ENTITY_TYPE> {

    fun isExpired(): Boolean

    fun setLastCacheTime(lastCacheTime: Long)
}