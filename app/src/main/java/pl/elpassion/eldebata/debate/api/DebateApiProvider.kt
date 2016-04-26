package pl.elpassion.eldebata.debate.api

import pl.elpassion.eldebata.base.retrofit.Provider
import pl.elpassion.eldebata.base.retrofit.RetrofitProvider
import retrofit2.http.GET
import retrofit2.http.Header
import rx.Observable

object DebateApiProvider : Provider<DebateApi> ({
    RetrofitProvider.get().create(DebateApi::class.java)
})

interface DebateApi {
    @GET("/debate")
    fun getDebateData(@Header("Authorization") authToken: String) : Observable<DebateData>
}

class DebateData (val topic : String, val answers: Answers)

class Answers (val positive : Answer, val negative: Answer, val neutral: Answer)

data class Answer (val id : Long, val value: String)
