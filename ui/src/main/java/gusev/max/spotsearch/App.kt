package gusev.max.spotsearch

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import gusev.max.spotsearch.injection.AppInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by v on 11/06/2018.
 */
class App : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        setupTimber()
        setupStetho()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    private fun setupStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }


}