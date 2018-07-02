package gusev.max.remote.pojo.base

import gusev.max.remote.model.BaseRemoteModel

/**
 * Created by v on 15/06/2018.
 */
data class BasePostPojo<M : BaseRemoteModel>(
    val entities: List<M>
)