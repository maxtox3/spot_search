package gusev.max.spotsearch.injection.module

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import gusev.max.cache.*
import gusev.max.cache.storage.SpotSearchDatabase
import gusev.max.data.source.action.ActionCache
import gusev.max.data.source.auth.AuthCache
import gusev.max.data.source.event.EventCache
import gusev.max.data.source.event.comment.CommentCache
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
                    SpotSearchDatabase::class.java, "debug6.db"
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

    @Binds
    abstract fun bindActionCache(actionCache: ActionCacheImpl): ActionCache

    @Binds
    abstract fun bindEventCache(eventCacheImpl: EventCacheImpl): EventCache

    @Binds
    abstract fun bindCommentCache(commentCacheImpl: CommentCacheImpl): CommentCache
}