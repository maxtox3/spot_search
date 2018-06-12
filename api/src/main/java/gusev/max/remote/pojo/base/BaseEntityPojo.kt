package gusev.max.remote.pojo.base

import gusev.max.remote.model.BaseRemoteModel

/**
 * Created by v on 11/06/2018.
 */
abstract class BaseEntityPojo<M : BaseRemoteModel> : BasePojo() {
    var entity: M? = null
}
