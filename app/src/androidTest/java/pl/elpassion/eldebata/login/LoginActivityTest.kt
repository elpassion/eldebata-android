package pl.elpassion.eldebata.login

import org.junit.Rule
import org.junit.Test
import pl.elpassion.eldebata.R
import pl.elpassion.eldebata.common.hasText
import pl.elpassion.eldebata.common.inputLayoutHasHint
import pl.elpassion.eldebata.common.onId
import pl.elpassion.eldebata.common.rule

class LoginActivityTest {

    @JvmField @Rule
    val rule = rule<LoginActivity>()

    @Test
    fun shouldHaveEditTextToInsertDebatesPinNumber() {
        onId(R.id.login_activity_pin_number).inputLayoutHasHint(R.string.login_activity_pin_number_hint)
    }

    @Test
    fun shouldHaveLoginButtonInTheMiddle() {
        onId(R.id.login_activity_login_button).hasText(R.string.login_activity_login_button_label)
    }

}