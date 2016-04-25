package pl.elpassion.eldebata.login

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.EditText
import pl.elpassion.eldebata.R
import pl.elpassion.eldebata.base.BaseActivity
import pl.elpassion.eldebata.base.retrofit.applySchedulers
import pl.elpassion.eldebata.login.api.LoginApiProvider
import pl.elpassion.eldebata.login.api.LoginResponse
import pl.elpassion.eldebata.prefs.AuthToken

class LoginActivity : BaseActivity() {

    private val submitButton by lazy { findViewById(R.id.login_activity_login_button) as View }
    private val codeInput by lazy { findViewById(R.id.login_activity_pin_number_edit_text) as EditText }
    private val api by lazy { LoginApiProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        setUpSubmitButton()
    }

    private fun setUpSubmitButton() {
        submitButton.setOnClickListener {
            api.login(codeInput.text.toString())
                    .applySchedulers()
                    .subscribe(onLoginSuccess, onLoginFailure)
        }
    }

    private val onLoginSuccess = { response: LoginResponse -> Unit
        AuthToken.save(response.authToken)
    }

    private val onLoginFailure = { throwable: Throwable -> Unit
        val snackbar = Snackbar.make(submitButton, R.string.login_activity_login_failure, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction(R.string.login_activity_login_failure_action, { snackbar.dismiss() })
                .show()
    }
}
