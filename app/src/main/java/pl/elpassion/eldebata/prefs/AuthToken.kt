package pl.elpassion.eldebata.prefs

import android.content.Context
import pl.elpassion.eldebata.base.ElDebataApplication.Companion.applicationContext

object AuthToken {

    private val authTokenKey = "authTokenKey"
    private val sharedPreferencesKey = "pl.elpassion.eldebata"
    val sharedPreferences = applicationContext.getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE)

    fun save(authToken: String){
         sharedPreferences.edit().putString(authTokenKey, authToken).commit()
    }

    fun read(): String? {
        if (isLoggedIn())
            return sharedPreferences.getString(authTokenKey, "")
        return null
    }

    fun isLoggedIn(): Boolean = sharedPreferences.contains(authTokenKey)
}
