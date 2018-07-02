package gusev.max.cache.storage

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by v on 10/06/2018.
 */
@Singleton
open class
PreferencesHelper @Inject constructor(context: Context) {

    companion object {
        private const val PREFS_PACKAGE_NAME = "gusev.max.spotsearch.preferences"

        private const val LAST_USERS_CACHE_DATE = "last_users_cache"
        private const val TOKEN = "token"

        private const val LAST_EVENTS_CACHE_TIME = "last_events_cache"
        private const val LAST_ACTIONS_CACHE_TIME = "last_actions_cache"
        private const val LAST_COMMENTS_CACHE_TIME = "last_comments_cache"

    }

    private val prefs: SharedPreferences

    init {
        prefs = context.getSharedPreferences(PREFS_PACKAGE_NAME, Context.MODE_PRIVATE)
    }

    var lastUsersChangeTime: Long
        get() = getTime(LAST_USERS_CACHE_DATE)
        set(value) = setTime(LAST_USERS_CACHE_DATE, value)

    var token: String
        get() = prefs.getString(TOKEN, "")
        set(value) = prefs.edit().putString(TOKEN, value).apply()

    var lastEventsCacheTime: Long
        get() = getTime(LAST_EVENTS_CACHE_TIME)
        set(value) = setTime(LAST_EVENTS_CACHE_TIME, value)

    var lastActionsCacheTime: Long
        get() = getTime(LAST_ACTIONS_CACHE_TIME)
        set(value) = setTime(LAST_ACTIONS_CACHE_TIME, value)

    var lastCommentsCacheTime: Long
        get() = getTime(LAST_COMMENTS_CACHE_TIME)
        set(value) = setTime(LAST_COMMENTS_CACHE_TIME, value)

    private fun getTime(key: String): Long {
        return prefs.getLong(key, 0L)
    }

    private fun setTime(key: String, value: Long) {
        prefs.edit().putLong(key, value).apply()
    }


}