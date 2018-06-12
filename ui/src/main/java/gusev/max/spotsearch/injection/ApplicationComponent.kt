package gusev.max.spotsearch.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import gusev.max.spotsearch.App
import gusev.max.spotsearch.injection.module.CacheModule
import gusev.max.spotsearch.injection.module.DomainModule
import gusev.max.spotsearch.injection.module.UiModule
import gusev.max.spotsearch.injection.module.ApplicationModule
import gusev.max.spotsearch.injection.module.DataModule
import gusev.max.spotsearch.injection.module.PresentationModule
import gusev.max.spotsearch.injection.module.RemoteModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(
                ApplicationModule::class,
                AndroidSupportInjectionModule::class,
                CacheModule::class,
                DataModule::class,
                DomainModule::class,
                PresentationModule::class,
                RemoteModule::class,
                UiModule::class
        )
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: App)

}