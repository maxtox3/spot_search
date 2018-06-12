package gusev.max.data.source.auth

import gusev.max.data.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by v on 20/04/2018.
 */
open class AuthCacheDataStore @Inject constructor(private val authCache: AuthCache) :
        AuthDataStore {

    override fun authorizeUser(userData: UserEntity): Flowable<UserEntity> {
        throw UnsupportedOperationException()
    }

    override fun registerUser(userData: UserEntity): Flowable<UserEntity> {
        throw UnsupportedOperationException()
    }

    override fun saveAuthData(userData: UserEntity): Completable {
        return authCache.saveAuthData(userData)
    }

    override fun me(): Single<UserEntity> {
        return authCache.me()
    }

}