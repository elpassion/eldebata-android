package pl.elpassion.eldebata.login.api

import pl.elpassion.eldebata.base.retrofit.Provider
import pl.elpassion.eldebata.base.retrofit.RetrofitProvider
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

object LoginApiProvider : Provider<LoginApi> ({
    RetrofitProvider.get().create(LoginApi::class.java)
})

interface LoginApi {

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("code") ticketType: String): Observable<LoginResponse>
}

data class LoginResponse(val authToken: String)
