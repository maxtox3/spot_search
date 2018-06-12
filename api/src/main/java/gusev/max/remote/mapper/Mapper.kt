package gusev.max.remote.mapper

import gusev.max.data.entity.BaseEntity
import gusev.max.remote.model.BaseRemoteModel

/**
 * Created by v on 10/06/2018.
 */
interface Mapper<in M: BaseRemoteModel, out E: BaseEntity> {

    fun mapFromRemote(type: M): E

}