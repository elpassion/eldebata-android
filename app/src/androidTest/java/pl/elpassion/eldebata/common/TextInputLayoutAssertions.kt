package pl.elpassion.eldebata.common

import android.support.annotation.StringRes
import android.support.design.widget.TextInputLayout
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.BoundedMatcher
import android.view.View
import org.hamcrest.Description

fun ViewInteraction.inputLayoutHasHint(@StringRes textId: Int) = check(matches(textInputLayoutHasHint(textId)))

private fun textInputLayoutHasHint(hintStringId: Int) = object : BoundedMatcher<View, TextInputLayout>(TextInputLayout::class.java) {

    override fun matchesSafely(view: TextInputLayout): Boolean {
        return view.hint == view.context.getString(hintStringId)
    }

    override fun describeTo(description: Description) {
        description.appendText("expecting $hintStringId")
    }
}
