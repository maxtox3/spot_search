package gusev.max.spotsearch.base.fragment.dialog

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import es.dmoral.toasty.Toasty
import gusev.max.presentation.base.BaseIntent
import gusev.max.presentation.base.BaseView
import gusev.max.presentation.base.BaseViewModel
import gusev.max.presentation.base.BaseViewState
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by v on 15/06/2018.
 */
abstract class BaseLceDialogFragment<
        INTENT : BaseIntent,
        VIEW_STATE : BaseViewState,
        VIEW_MODEL : BaseViewModel<INTENT, VIEW_STATE>>
    : BaseDialogFragment(), BaseView<INTENT, VIEW_STATE> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: VIEW_MODEL
    protected val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = getViewModel()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    abstract fun getViewModel(): VIEW_MODEL

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeForUpdatesFromViewModel()
        processIntents()
    }

    protected open fun processIntents() {
        viewModel.processIntents(intents())
    }

    protected open fun subscribeForUpdatesFromViewModel() {
        compositeDisposable.add(viewModel.states().subscribe({ render(it) }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    abstract fun getLoadingView(): View

    protected fun setupScreenForError(error: String) {
        getLoadingView().visibility = View.GONE
        Toasty.error(context!!, error, Toast.LENGTH_SHORT).show()
    }

    protected fun setupScreenForLoading() {
        getLoadingView().visibility = View.VISIBLE
    }
}