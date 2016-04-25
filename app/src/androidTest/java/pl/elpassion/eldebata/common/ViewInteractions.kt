package pl.elpassion.eldebata.common

import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText

fun onId(@IdRes viewId: Int) = onView(withId(viewId))

fun onText(@StringRes textId: Int) = onView(withText(textId))
