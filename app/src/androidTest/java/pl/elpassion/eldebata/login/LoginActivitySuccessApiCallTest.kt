package pl.elpassion.eldebata.login

import android.support.test.espresso.Espresso.closeSoftKeyboard
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as on
import pl.elpassion.eldebata.R
import pl.elpassion.eldebata.common.*
import pl.elpassion.eldebata.login.api.LoginApi
import pl.elpassion.eldebata.login.api.LoginApiProvider
import pl.elpassion.eldebata.login.api.LoginResponse
import pl.elpassion.eldebata.prefs.AuthToken
import rx.Observable

class LoginActivitySuccessApiCallTest {

    val api = Mockito.mock(LoginApi::class.java)

    @JvmField @Rule
    val rule = rule<LoginActivity> {
        on(api.login(anyString())).thenReturn(Observable.just(LoginResponse("authToken")))
        LoginApiProvider.override = api
    }

    @Test
    fun shouldSaveReturnedTokenInSharedPreferences() {
        loginWithCode("12345")
        Assert.assertTrue(AuthToken.isLoggedIn())
    }

    private fun loginWithCode(code: String) {
        onId(R.id.login_activity_pin_number_edit_text).typeText(code)
        closeSoftKeyboard()
        onId(R.id.login_activity_login_button).click()
    }

}