package gusev.max.spotsearch.injection.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import gusev.max.presentation.auth.AuthViewModel
import gusev.max.presentation.main.actions.ActionsViewModel
import gusev.max.presentation.main.event_full_info.EventFullInfoViewModel
import gusev.max.presentation.main.map.MapViewModel
import gusev.max.spotsearch.injection.ViewModelFactory
import kotlin.reflect.KClass

/**
 * Annotation class to identify view models by classname.
 */
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

/**
 * Module that provides all dependencies from the presentation package/layer.
 */
@Module
abstract class PresentationModule {

    /**
     * Auth
     */

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

    /**
     * Main
     */

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun bindMapViewModel(viewModel: MapViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ActionsViewModel::class)
    abstract fun bindActionsViewModel(viewModel: ActionsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EventFullInfoViewModel::class)
    abstract fun bindEventFullInfoViewModel(viewModel: EventFullInfoViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
