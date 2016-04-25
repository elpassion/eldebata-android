package pl.elpassion.eldebata.login

import android.support.test.espresso.Espresso
import android.support.test.espresso.matcher.ViewMatchers.withText
import org.junit.Rule
import org.junit.Test
import pl.elpassion.eldebata.common.isDisplayed
import pl.elpassion.eldebata.common.rule

class LoginActivityTest {

    @JvmField @Rule
    val rule = rule<LoginActivity>()

    @Test
    fun initTest() {
        Espresso.onView(withText("ElDebata!")).isDisplayed()
    }

}