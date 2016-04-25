package pl.elpassion.eldebata.login

import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import pl.elpassion.eldebata.R
import pl.elpassion.eldebata.common.hasText
import pl.elpassion.eldebata.common.inputLayoutHasHint
import pl.elpassion.eldebata.common.onId
import pl.elpassion.eldebata.common.rule
import pl.elpassion.eldebata.login.api.LoginApi
import pl.elpassion.eldebata.login.api.LoginApiProvider
import rx.Observable
import org.mockito.Mockito.`when` as on

class LoginActivityTest {

    val api = Mockito.mock(LoginApi::class.java)

    @JvmField @Rule
    val rule = rule<LoginActivity> {
        on(api.login(anyString())).thenReturn(Observable.never())
        LoginApiProvider.override = api
    }

    @Test
    fun shouldHaveEditTextToInsertDebatesPinNumber() {
        onId(R.id.login_activity_pin_number).inputLayoutHasHint(R.string.login_activity_pin_number_hint)
    }

    @Test
    fun shouldHaveLoginButtonInTheMiddle() {
        onId(R.id.login_activity_login_button).hasText(R.string.login_activity_login_button_label)
    }

    @Test
    fun shouldMakeCallToApiWithCorrectPinWhenLoginButtonIsPressed() {
        loginWithCode("12345")
        verify(api, times(1)).login("12345")
    }

}