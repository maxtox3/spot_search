package gusev.max.data.source.auth

/**
 * Created by v on 19/04/2018.
 */
interface AuthCache : AuthDataStore {

    fun isExpired(): Boolean

    fun setLastCacheTime(lastCacheTime: Long)

    fun saveToken(token: String)

    fun getToken(): String
}