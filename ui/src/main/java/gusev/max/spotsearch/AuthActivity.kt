package gusev.max.spotsearch

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import dagger.android.AndroidInjection
import es.dmoral.toasty.Toasty
import gusev.max.presentation.auth.AuthIntent
import gusev.max.presentation.auth.AuthViewModel
import gusev.max.presentation.auth.AuthViewState
import gusev.max.presentation.base.BaseView
import gusev.max.spotsearch.utils.AnimationUtils
import gusev.max.spotsearch.utils.InputLayoutTextWatcherErrorFixer
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject


class AuthActivity : AppCompatActivity(), BaseView<AuthIntent, AuthViewState> {

    private val loginPublisher: PublishSubject<AuthIntent.LoginIntent> = PublishSubject.create()
    private val signUpPublisher: PublishSubject<AuthIntent.SignUpIntent> = PublishSubject.create()
    private var isAuthCurrent: Boolean = true

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: AuthViewModel
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        AndroidInjection.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(AuthViewModel::class.java)

        compositeDisposable.add(viewModel.states().subscribe({ render(it) }))
        viewModel.processIntents(intents())

        setupWidgets()
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    override fun intents(): Observable<AuthIntent> {
        return Observable.merge(
                Observable.just(AuthIntent.InitialIntent),
                Observable.just(AuthIntent.CheckAuthIntent),
                loginPublisher,
                signUpPublisher
        )
    }

    override fun render(state: AuthViewState) {
        when (state) {
            is AuthViewState.InProgress -> setupScreenForLoadingState()
            is AuthViewState.SignUpSuccess -> navigateToMain()
            is AuthViewState.LoginSuccess -> navigateToMain()
            is AuthViewState.SignUpFailed -> setupScreenForError(state.signUpErrorMessage)
            is AuthViewState.AuthFailed -> setupScreenForError(state.loginErrorMessage)
            is AuthViewState.CheckAuthFailed -> showContent()
        }
    }

    private fun showContent() {
        start.visibility = View.GONE
        scroll_view.visibility = View.VISIBLE
    }

    private fun setupScreenForLoadingState() {
        progress.visibility = View.VISIBLE
    }

    private fun setupScreenForError(error: String?) {
        progress.visibility = View.GONE
        Toasty.error(this, "$error", Toast.LENGTH_SHORT, true).show()
    }

    private fun navigateToMain() {
        progress.visibility = View.GONE
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun setupWidgets() {
        initEditTexts()
        initButtons()
    }

    private fun initEditTexts() {
        var frameLayout = edit_email.parent as FrameLayout
        val params = frameLayout.layoutParams as LinearLayout.LayoutParams
        params.setMargins(0, 0, 0, 1)
        frameLayout.layoutParams = params
        frameLayout = edit_password.parent as FrameLayout
        frameLayout.layoutParams = params
        frameLayout = edit_name.parent as FrameLayout
        frameLayout.layoutParams = params

        edit_name.setOnEditorActionListener(
                { v, actionId, event ->
                    if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                        btn_go.performClick()
                        return@setOnEditorActionListener true
                    }
                    return@setOnEditorActionListener false
                }
        )
        fixErrorUpdate()
    }

    private fun fixErrorUpdate() {
        edit_email.addTextChangedListener(InputLayoutTextWatcherErrorFixer(input_layout_email))
        edit_name.addTextChangedListener(InputLayoutTextWatcherErrorFixer(input_layout_name))
        edit_password.addTextChangedListener(InputLayoutTextWatcherErrorFixer(input_layout_password))
    }

    private fun initButtons() {
        val login = resources.getString(R.string.login)
        val signup = resources.getString(R.string.register)
        btn_go.setOnClickListener({ v ->
            if (!fieldsHasErrors()) {
                go()
            }
        })
        btn_change.setOnClickListener({ v ->
            isAuthCurrent = !isAuthCurrent
            if (isAuthCurrent) {
                input_layout_name.visibility = View.GONE
                btn_go.text = login
                btn_change.text = signup
            } else {
                input_layout_name.visibility = View.VISIBLE
                btn_go.text = signup
                btn_change.text = login
            }

        })
    }

    private fun go() {
        if (isAuthCurrent) {
            loginPublisher.onNext(
                    AuthIntent.LoginIntent(
                            email = edit_email.text.toString(),
                            password = edit_password.text.toString()
                    )
            )
        } else {
            signUpPublisher.onNext(
                    AuthIntent.SignUpIntent(
                            name = edit_name.text.toString(),
                            email = edit_email.text.toString(),
                            password = edit_password.text.toString()
                    )
            )
        }
    }

    private fun fieldsHasErrors(): Boolean {
        var error = false

        if (edit_email.text.length < 4) {
            AnimationUtils.shake(input_layout_email)
            input_layout_email.isErrorEnabled = true
            input_layout_email.error = "Email должен содержать более 4 символов"
            error = true
        }
        if (!isAuthCurrent) {
            if (edit_name.text.length < 3) {
                AnimationUtils.shake(input_layout_name)
                input_layout_name.isErrorEnabled = true
                input_layout_name.error = "Имя должно содержать более 3 символов"
                error = true
            }
        }

        if (edit_password.text.length < 6) {
            AnimationUtils.shake(input_layout_password)
            input_layout_password.isErrorEnabled = true
            input_layout_password.error = "Пароль должен быть более 6 символов"
            error = true
        }

        return error
    }

}