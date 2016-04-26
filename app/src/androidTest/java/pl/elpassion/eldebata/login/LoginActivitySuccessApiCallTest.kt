package pl.elpassion.eldebata.login

import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.anyString
import pl.elpassion.eldebata.common.rule
import pl.elpassion.eldebata.login.api.LoginApi
import pl.elpassion.eldebata.login.api.LoginApiProvider
import pl.elpassion.eldebata.login.api.LoginResponse
import pl.elpassion.eldebata.prefs.AuthToken
import rx.Observable
import org.mockito.Mockito.`when` as on

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

    @Test
    fun shouldReallySaveReturnedAuthToken() {
        loginWithCode("12345")
        Assert.assertEquals("Token token=authToken", AuthToken.read())
    }

}