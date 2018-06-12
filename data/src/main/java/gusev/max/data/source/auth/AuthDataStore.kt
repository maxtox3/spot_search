package gusev.max.data.source.auth

import gusev.max.data.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by v on 19/04/2018.
 */
interface AuthDataStore {

    fun registerUser(userData: UserEntity): Flowable<UserEntity>

    fun authorizeUser(userData: UserEntity): Flowable<UserEntity>

    fun saveAuthData(userData: UserEntity): Completable

    fun me(): Single<UserEntity>
}