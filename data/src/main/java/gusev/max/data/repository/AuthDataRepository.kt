package gusev.max.data.repository

import gusev.max.data.mapper.UserMapper
import gusev.max.data.sourcefactory.AuthDataStoreFactory
import gusev.max.domain.model.User
import gusev.max.domain.repository.AuthRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by v on 11/06/2018.
 */
class AuthDataRepository @Inject constructor(
    private val factory: AuthDataStoreFactory,
    private val userMapper: UserMapper
) : AuthRepository {

    override fun authorizeUser(user: User): Flowable<User> {
        return factory.retrieveRemoteDataStore().authorizeUser(userMapper.mapToEntity(user))
            .flatMap {
                factory.retrieveCacheDataStore().saveAuthData(it).toSingle { it }.toFlowable()
            }
            .map {
                userMapper.mapFromEntity(it)
            }
    }

    override fun registerUser(user: User): Flowable<User> {
        return factory.retrieveRemoteDataStore().registerUser(userMapper.mapToEntity(user))
            .map {
                userMapper.mapFromEntity(it)
            }
    }

    override fun logout(): Completable {
        throw UnsupportedOperationException()
    }

    override fun checkAuth(): Completable {
        return factory.retrieveRemoteDataStore().me().flatMapCompletable { Completable.complete() }
    }


}