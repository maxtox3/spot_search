package gusev.max.data.source.auth

import gusev.max.data.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by v on 20/04/2018.
 */
open class AuthRemoteDataStore @Inject constructor(private val authRemote: AuthRemote) :
        AuthDataStore {

    override fun authorizeUser(userData: UserEntity): Flowable<UserEntity> {
        return authRemote.authorizeUser(userData)
    }

    override fun registerUser(userData: UserEntity): Flowable<UserEntity> {
        return authRemote.registerUser(userData)
    }

    override fun saveAuthData(userData: UserEntity): Completable {
        throw UnsupportedOperationException()
    }

    override fun me(): Single<UserEntity> {
        return authRemote.me()
    }
}