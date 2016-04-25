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

class VotingActivity : BaseActivity() {

    val api by lazy { DebateApiProvider.get() }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, VotingActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.voting_activity)
        api.getDebateData().applySchedulers().subscribe(onGetDebateDataSuccess, onGetDebateDataFailure)
    }

    val onGetDebateDataSuccess: (DebateData) -> Unit = {
        val topic = findViewById(R.id.voting_activity_debate_topic) as TextView
        topic.text = it.topic
    }

    val onGetDebateDataFailure: (Throwable) -> Unit = {}
}