package pl.elpassion.eldebata.debate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import pl.elpassion.eldebata.R
import pl.elpassion.eldebata.base.BaseActivity
import pl.elpassion.eldebata.base.retrofit.applySchedulers
import pl.elpassion.eldebata.debate.api.DebateApiProvider
import pl.elpassion.eldebata.debate.api.DebateData
import pl.elpassion.eldebata.debate.api.VoteApiProvider
import pl.elpassion.eldebata.prefs.AuthToken

class VotingActivity : BaseActivity() {

    val debateDataApi by lazy { DebateApiProvider.get() }
    val voteApi by lazy { VoteApiProvider.get() }
    val topic by lazy { findViewById(R.id.voting_activity_debate_topic) as TextView }
    val positiveVote by lazy { findViewById(R.id.voting_activity_positive_vote_button) as TextView }
    val negativeVote by lazy { findViewById(R.id.voting_activity_negative_vote_button) as TextView }
    val neutralVote by lazy { findViewById(R.id.voting_activity_neutral_vote_button) as TextView }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, VotingActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.voting_activity)
        debateDataApi.getDebateData(AuthToken.read()!!).applySchedulers().subscribe(onGetDebateDataSuccess, onGetDebateDataFailure)
    }

    val onGetDebateDataSuccess = { debateData: DebateData -> Unit
        val authToken = AuthToken.read()!!
        topic.text = debateData.topic
        positiveVote.text = debateData.answers.positive.value
        positiveVote.setOnClickListener { voteApi.vote(authToken, debateData.answers.positive) }
        negativeVote.text = debateData.answers.negative.value
        negativeVote.setOnClickListener { voteApi.vote(authToken, debateData.answers.negative) }
        neutralVote.text = debateData.answers.neutral.value
        neutralVote.setOnClickListener { voteApi.vote(authToken, debateData.answers.neutral) }
    }

    val onGetDebateDataFailure: (Throwable) -> Unit = {}
}