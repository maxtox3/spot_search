package gusev.max.data.source.action

import gusev.max.data.entity.ActionEntity
import gusev.max.data.source.base.BaseRemoteDataStore
import javax.inject.Inject

/**
 * Created by v on 13/06/2018.
 */
class ActionRemoteDataStore @Inject constructor(
    remoteStore: ActionRemote
) : BaseRemoteDataStore<ActionEntity>(remoteStore), ActionDataStore