package pl.elpassion.eldebata.debate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.Snackbar.LENGTH_INDEFINITE
import android.support.design.widget.Snackbar.LENGTH_LONG
import android.util.Log
import android.widget.TextView
import pl.elpassion.eldebata.R
import pl.elpassion.eldebata.base.BaseActivity
import pl.elpassion.eldebata.base.retrofit.applySchedulers
import pl.elpassion.eldebata.debate.api.Answer
import pl.elpassion.eldebata.debate.api.DebateApiProvider
import pl.elpassion.eldebata.debate.api.DebateData
import pl.elpassion.eldebata.debate.api.VoteApiProvider
import pl.elpassion.eldebata.prefs.AuthToken
import rx.Subscription

class VotingActivity : BaseActivity() {

    val debateDataApi by lazy { DebateApiProvider.get() }
    val voteApi by lazy { VoteApiProvider.get() }
    val topic by lazy { findViewById(R.id.voting_activity_debate_topic) as TextView }
    val positiveVote by lazy { findViewById(R.id.voting_activity_positive_vote_button) as TextView }
    val negativeVote by lazy { findViewById(R.id.voting_activity_negative_vote_button) as TextView }
    val neutralVote by lazy { findViewById(R.id.voting_activity_neutral_vote_button) as TextView }
    val authToken = AuthToken.read()!!
    var subscription: Subscription? = null

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, VotingActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.voting_activity)
        loadDebateData()
    }

    private fun loadDebateData() {
        subscription?.unsubscribe()
        subscription = debateDataApi.getDebateData(authToken)
                .applySchedulers()
                .subscribe(onGetDebateDataSuccess, onGetDebateDataFailure)
    }

    val onGetDebateDataSuccess = { debateData: DebateData -> Unit
        topic.text = debateData.topic
        setUpButtons(debateData)
    }

    private fun setUpButtons(debateData: DebateData) {
        setUpPositiveButton(debateData.answers.positive)
        setUpNegativeButton(debateData.answers.negative)
        setUpNeutralButton(debateData.answers.neutral)
    }

    private fun setUpPositiveButton(answer: Answer) {
        positiveVote.text = answer.value
        positiveVote.setOnClickListener {
            vote(answer)
        }
    }

    private fun setUpNegativeButton(answer: Answer) {
        negativeVote.text = answer.value
        negativeVote.setOnClickListener {
            vote(answer)
        }
    }

    private fun setUpNeutralButton(answer: Answer) {
        neutralVote.text = answer.value
        neutralVote.setOnClickListener {
            vote(answer)
        }
    }

    private fun vote(answer: Answer) {
        subscription?.unsubscribe()
        subscription = voteApi.vote(authToken, answer)
                .applySchedulers().subscribe(onVoteSuccess, onVoteFailure)
    }

    val onVoteSuccess: (Void) -> Unit = {
        Snackbar.make(topic, R.string.voting_activity_vote_success, LENGTH_LONG)
                .show()
    }

    val onVoteFailure: (Throwable) -> Unit = {
        Snackbar.make(topic, R.string.voting_activity_vote_failure, LENGTH_LONG)
                .show()
    }

    val onGetDebateDataFailure: (Throwable) -> Unit = {
        Log.e("GetDebateDataFailure", "", it)
        Snackbar.make(topic, R.string.voting_activity_get_debate_data_failure, LENGTH_INDEFINITE)
                .setAction(R.string.voting_activity_get_debate_data_retry, { loadDebateData() })
                .show()
    }
}