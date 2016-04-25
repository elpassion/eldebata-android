/*
 * Copyright (c) 2016.  Upnext Technologies Sp. z o.o.
 * All rights reserved.
 *
 * This source code is licensed under the BSD 3-Clause License found in the
 * LICENSE.txt file in the root directory of this source tree.
 */

package pl.elpassion.eldebata.common

import android.app.Activity
import android.support.test.rule.ActivityTestRule


inline fun <reified T : Activity> rule(): ActivityTestRule<T> {
    return object : ActivityTestRule<T>(T::class.java) {}
}
