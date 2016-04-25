package pl.elpassion.eldebata.login

import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import org.junit.Rule
import org.junit.Test
import pl.elpassion.eldebata.common.rule

class LoginActivityTest {

    @JvmField @Rule
    val rule = rule<LoginActivity>()

    @Test
    fun initTest() {
        Espresso.onView(ViewMatchers.withText("ElDebata!")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}