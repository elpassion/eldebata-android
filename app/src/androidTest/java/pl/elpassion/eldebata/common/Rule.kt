package pl.elpassion.eldebata.common

import android.app.Activity
import android.support.test.rule.ActivityTestRule


inline fun <reified T : Activity> rule(): ActivityTestRule<T> {
    return object : ActivityTestRule<T>(T::class.java) {}
}

inline fun <reified T : Activity> rule(crossinline beforeActivityFunction: () -> Unit): ActivityTestRule<T> {
    return object : ActivityTestRule<T>(T::class.java) {
        override fun beforeActivityLaunched() {
            beforeActivityFunction()
        }
    }
}