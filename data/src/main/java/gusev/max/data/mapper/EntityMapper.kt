package gusev.max.data.mapper

import gusev.max.data.entity.BaseEntity
import gusev.max.domain.model.BaseDomainModel

/**
 * Created by v on 10/06/2018.
 */
interface EntityMapper<E : BaseEntity, D : BaseDomainModel> {

    fun mapFromEntity(type: E): D

    fun mapToEntity(type: D): E

}