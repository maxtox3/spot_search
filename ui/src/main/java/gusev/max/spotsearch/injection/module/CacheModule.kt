package gusev.max.spotsearch.injection.module

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import gusev.max.cache.AuthCacheImpl
import gusev.max.cache.UserCacheImpl
import gusev.max.cache.storage.SpotSearchDatabase
import gusev.max.data.source.auth.AuthCache
import gusev.max.data.source.user.UserCache

/**
 * Module that provides all dependencies from the cache package/layer.
 */
@Module
abstract class CacheModule {

    /**
     * This companion object annotated as a module is necessary in order to provide dependencies
     * statically in case the wrapping module is an abstract class (to use binding)
     */
    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideSpotSearchDatabase(application: Application): SpotSearchDatabase {
            return Room.databaseBuilder(
                    application.applicationContext,
                    SpotSearchDatabase::class.java, "debug1.db"
            ).build()
        }
    }

    /**
     * Auth
     */

    @Binds
    abstract fun bindUserCache(userCacheImpl: UserCacheImpl): UserCache

    @Binds
    abstract fun bindAuthCache(authCacheImpl: AuthCacheImpl): AuthCache

    /**
     * Main
     */
}