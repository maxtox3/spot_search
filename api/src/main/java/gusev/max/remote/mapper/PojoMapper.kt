package gusev.max.remote.mapper

import gusev.max.remote.model.BaseRemoteModel
import gusev.max.remote.pojo.base.BasePostPojo
import javax.inject.Inject

/**
 * Created by v on 15/06/2018.
 */
class PojoMapper<M : BaseRemoteModel> @Inject constructor() {

    fun mapToPostPojo(models: List<M>): BasePostPojo<M> {
        return BasePostPojo(models)
    }
}