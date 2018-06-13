package gusev.max.remote

import gusev.max.data.entity.ActionEntity
import gusev.max.data.source.action.ActionRemote
import gusev.max.remote.api.ActionApi
import gusev.max.remote.mapper.ActionMapper
import gusev.max.remote.model.ActionModel
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by v on 13/06/2018.
 */
class ActionRemoteImpl @Inject constructor(
    private val service: ActionApi,
    actionMapper: ActionMapper
) : BaseRemoteImpl<ActionEntity, ActionModel>(actionMapper), ActionRemote {

    override fun getEntities(): Flowable<List<ActionEntity>> {
        return service.getActions()
            .map {
                it.entities?.map {
                    mapper.mapFromRemote(it)
                } ?: arrayListOf()
            }
    }

    override fun getEntityById(id: Long): Flowable<ActionEntity> {
        throw UnsupportedOperationException()
    }

}