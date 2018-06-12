package gusev.max.remote.pojo.base

import gusev.max.remote.model.BaseRemoteModel

/**
 * Created by v on 10/06/2018.
 */
abstract class BaseEntitiesListPojo<M : BaseRemoteModel> : BasePojo() {
    var entities: List<M>? = null
}