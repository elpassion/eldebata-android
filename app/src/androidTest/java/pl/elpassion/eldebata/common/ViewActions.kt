package pl.elpassion.eldebata.common


import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions

fun ViewInteraction.click() = perform(ViewActions.click())

fun ViewInteraction.scrollTo() = perform(ViewActions.scrollTo())

fun ViewInteraction.typeText(text: String) = perform(ViewActions.typeText(text))

fun ViewInteraction.pressImeActionButton() = perform(ViewActions.pressImeActionButton())
