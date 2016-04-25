package pl.elpassion.eldebata.login

import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.anyString
import pl.elpassion.eldebata.R
import pl.elpassion.eldebata.common.hasText
import pl.elpassion.eldebata.common.onId
import pl.elpassion.eldebata.common.rule
import pl.elpassion.eldebata.login.api.LoginApi
import pl.elpassion.eldebata.login.api.LoginApiProvider
import rx.Observable
import org.mockito.Mockito.`when` as on

class LoginActivityFailureApiCallTest {

    val api = Mockito.mock(LoginApi::class.java)

    @JvmField
    @Rule
    val rule = rule<LoginActivity> {
        on(api.login(anyString())).thenReturn(Observable.error(Exception()))
        LoginApiProvider.override = api
    }

    @Test
    fun shouldShowErrorWhenApiCALLFails() {
        loginWithCode("12345")
        onId(R.id.snackbar_text).hasText(R.string.login_activity_login_failure)
    }

}