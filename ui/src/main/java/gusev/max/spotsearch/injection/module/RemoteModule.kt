package gusev.max.spotsearch.injection.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import gusev.max.data.source.action.ActionRemote
import gusev.max.data.source.auth.AuthCache
import gusev.max.data.source.auth.AuthRemote
import gusev.max.data.source.event.EventRemote
import gusev.max.data.source.event.comment.CommentRemote
import gusev.max.data.source.user.UserRemote
import gusev.max.remote.*
import gusev.max.remote.api.*
import gusev.max.spotsearch.BuildConfig

/**
 * Module that provides all dependencies from the repository package/layer.
 */
@Module
abstract class RemoteModule {

    /**
     * This companion object annotated as a module is necessary in order to provide dependencies
     * statically in case the wrapping module is an abstract class (to use binding)
     */
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideAuthApi(cache: AuthCache): AuthApi {
            return SpotSearchServiceFactory.makeAuthService(
                    BuildConfig.DEBUG,
                    BuildConfig.BASE_URL,
                    cache
            )
        }

        @Provides
        @JvmStatic
        fun provideUserApi(cache: AuthCache): UserApi {
            return SpotSearchServiceFactory.makeUserService(
                    BuildConfig.DEBUG,
                    BuildConfig.BASE_URL,
                    cache
            )
        }

        @Provides
        @JvmStatic
        fun provideActionApi(cache: AuthCache): ActionApi {
            return SpotSearchServiceFactory.makeActionService(
                    BuildConfig.DEBUG,
                    BuildConfig.BASE_URL,
                    cache
            )
        }

        @Provides
        @JvmStatic
        fun provideEventApi(cache: AuthCache): EventApi {
            return SpotSearchServiceFactory.makeEventService(
                    BuildConfig.DEBUG,
                    BuildConfig.BASE_URL,
                    cache
            )
        }

        @Provides
        @JvmStatic
        fun provideCommentApi(cache: AuthCache): CommentApi {
            return SpotSearchServiceFactory.makeCommentService(
                    BuildConfig.DEBUG,
                    BuildConfig.BASE_URL,
                    cache
            )
        }
    }

    /**
     * Auth
     */

    @Binds
    abstract fun bindUserRemote(userRemoteImpl: UserRemoteImpl): UserRemote

    @Binds
    abstract fun bindAuthRemote(authRemoteImpl: AuthRemoteImpl): AuthRemote

    /**
     * Main
     */

    @Binds
    abstract fun bindActionRemote(actionRemoteImpl: ActionRemoteImpl): ActionRemote

    @Binds
    abstract fun bindEventRemote(eventRemoteImpl: EventRemoteImpl): EventRemote

    @Binds
    abstract fun bindCommentRemote(commentRemoteImpl: CommentRemoteImpl): CommentRemote
}