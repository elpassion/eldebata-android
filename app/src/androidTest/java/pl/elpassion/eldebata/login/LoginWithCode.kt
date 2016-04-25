package pl.elpassion.eldebata.login

import android.support.test.espresso.Espresso
import pl.elpassion.eldebata.R
import pl.elpassion.eldebata.common.click
import pl.elpassion.eldebata.common.onId
import pl.elpassion.eldebata.common.typeText

internal fun loginWithCode(code: String) {
    onId(R.id.login_activity_pin_number_edit_text).typeText(code)
    Espresso.closeSoftKeyboard()
    onId(R.id.login_activity_login_button).click()
}