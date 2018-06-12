package gusev.max.spotsearch.injection.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import gusev.max.data.source.auth.AuthCache
import gusev.max.data.source.auth.AuthRemote
import gusev.max.data.source.user.UserRemote
import gusev.max.remote.AuthRemoteImpl
import gusev.max.remote.SpotSearchServiceFactory
import gusev.max.remote.UserRemoteImpl
import gusev.max.remote.api.AuthApi
import gusev.max.remote.api.UserApi
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


}