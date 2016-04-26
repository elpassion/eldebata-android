package pl.elpassion.eldebata.debate.api

import pl.elpassion.eldebata.base.retrofit.Provider
import pl.elpassion.eldebata.base.retrofit.RetrofitProvider
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import rx.Observable

object VoteApiProvider : Provider<VoteApi> ({
    RetrofitProvider.get().create(VoteApi::class.java)
})

interface VoteApi {

    @POST("/vote.json")
    fun getDebateData(@Header("Authorization") authToken: String, @Body vote: Answer): Observable<DebateData>
}
