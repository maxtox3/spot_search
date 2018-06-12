package gusev.max.cache

import gusev.max.cache.storage.PreferencesHelper
import gusev.max.cache.storage.SpotSearchDatabase
import gusev.max.data.entity.UserEntity
import gusev.max.data.source.auth.AuthCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by v on 10/06/2018.
 */
class AuthCacheImpl @Inject constructor(
    private val database: SpotSearchDatabase,
    private val preferencesHelper: PreferencesHelper
) : AuthCache {

    private val EXPERATION_TIME = (60 * 10 * 1000).toLong()

    override fun saveAuthData(userData: UserEntity): Completable {
        return Completable.defer {
            setLastCacheTime(System.currentTimeMillis())
            saveToken(userData.token)
            Completable.complete()
        }
    }

    override fun saveToken(token: String) {
        preferencesHelper.token = token
    }

    override fun getToken(): String {
        return preferencesHelper.token
    }

    override fun isExpired(): Boolean {
        val currTime = System.currentTimeMillis()
        val lastUpdate = getLastUpdateTime()
        return currTime - lastUpdate > EXPERATION_TIME
    }

    override fun setLastCacheTime(lastCacheTime: Long) {
        preferencesHelper.lastUsersChangeTime = lastCacheTime
    }

    private fun getLastUpdateTime(): Long {
        return preferencesHelper.lastUsersChangeTime
    }

    override fun registerUser(userData: UserEntity): Flowable<UserEntity> {
        throw UnsupportedOperationException()
    }

    override fun authorizeUser(userData: UserEntity): Flowable<UserEntity> {
        throw UnsupportedOperationException()
    }

    override fun me(): Single<UserEntity> {
        throw UnsupportedOperationException()
    }
}