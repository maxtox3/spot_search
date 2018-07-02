package gusev.max.spotsearch.injection.module

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import gusev.max.domain.executor.PostExecutionThread
import gusev.max.spotsearch.auth.AuthActivity
import gusev.max.spotsearch.main.MainActivity
import gusev.max.spotsearch.main.actions.ActionsListFragment
import gusev.max.spotsearch.main.event.CreateEventDialogFragment
import gusev.max.spotsearch.main.event.EventFullInfoFragment
import gusev.max.spotsearch.main.event.MapFragment
import gusev.max.spotsearch.utils.UiThread

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

    @ContributesAndroidInjector
    abstract fun contributeActionsListFragment(): ActionsListFragment

    @ContributesAndroidInjector
    abstract fun contributeMapFragment(): MapFragment

    @ContributesAndroidInjector
    abstract fun contributeCreateEventDialog(): CreateEventDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeEventFullInfoFragment(): EventFullInfoFragment


}