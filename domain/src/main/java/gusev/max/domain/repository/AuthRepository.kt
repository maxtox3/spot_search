package gusev.max.domain.repository

import gusev.max.domain.model.User
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by v on 10/06/2018.
 */
interface AuthRepository {

    fun authorizeUser(user: User): Flowable<User>

    fun registerUser(user: User): Flowable<User>

    fun logout(): Completable

    fun checkAuth(): Completable

}