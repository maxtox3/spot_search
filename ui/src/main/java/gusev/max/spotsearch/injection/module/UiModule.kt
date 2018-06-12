package gusev.max.spotsearch.injection.module

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import gusev.max.domain.executor.PostExecutionThread
import gusev.max.spotsearch.AuthActivity
import gusev.max.spotsearch.MainActivity
import gusev.max.spotsearch.UiThread

/**
 * Module that provides all dependencies from the mobile-ui package/layer and injects all activities.
 */
@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    /**
     * Auth
     */
    @ContributesAndroidInjector
    abstract fun contributeAuthActivity(): AuthActivity


    /**
     * Main
     */
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity


}