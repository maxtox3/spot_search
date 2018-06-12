package gusev.max.remote

import gusev.max.data.entity.UserEntity
import gusev.max.data.source.user.UserRemote
import gusev.max.remote.api.UserApi
import gusev.max.remote.mapper.UserMapper
import gusev.max.remote.model.UserModel
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by v on 11/06/2018.
 */
class UserRemoteImpl @Inject constructor(
    private val service: UserApi,
    private val userMapper: UserMapper
) : BaseRemoteImpl<UserEntity, UserModel>(userMapper), UserRemote {

    override fun getEntities(): Flowable<List<UserEntity>> {
        throw UnsupportedOperationException()
    }

    override fun getEntityById(id: Long): Flowable<UserEntity> {
        return service.getUser(id)
            //todo add errorfilter
            .map {
                userMapper.mapFromRemote(it.entity!!)
            }
    }

}