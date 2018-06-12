package gusev.max.remote

import gusev.max.data.entity.UserEntity
import gusev.max.data.source.auth.AuthRemote
import gusev.max.domain.error.AuthError
import gusev.max.remote.api.AuthApi
import gusev.max.remote.mapper.UserMapper
import gusev.max.remote.pojo.auth.AuthResponsePojo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by v on 11/06/2018.
 */

class AuthRemoteImpl @Inject constructor(
    private val service: AuthApi,
    private val userMapper: UserMapper
) : AuthRemote {

    private val authErrorTransformer: FlowableTransformer<AuthResponsePojo, AuthResponsePojo> = FlowableTransformer {
        it.switchMap {
            if (it.error != null || it.error?.errors != null) {
                Flowable.error(AuthError(it.error.message ?: ""))
            } else {
                Flowable.just(it)
            }
        }
    }

    override fun registerUser(userData: UserEntity): Flowable<UserEntity> {
        return service.signUp(userData.name, userData.email, userData.password)
            .compose(authErrorTransformer)
            .map {
                it.user?.token = it.token
                it.user!!
            }.map {
                userMapper.mapFromRemote(it)
            }
    }

    override fun authorizeUser(userData: UserEntity): Flowable<UserEntity> {
        return service.login(userData.email, userData.password)
            .compose(authErrorTransformer)
            .map {
                it.user?.token = it.token
                it.user!!
            }
            .map {
                userMapper.mapFromRemote(it)
            }
    }

    override fun me(): Single<UserEntity> {
        return service.me()
            .map {
                userMapper.mapFromRemote(it.user!!)
            }
    }


    override fun saveAuthData(userData: UserEntity): Completable {
        throw UnsupportedOperationException()
    }
}
