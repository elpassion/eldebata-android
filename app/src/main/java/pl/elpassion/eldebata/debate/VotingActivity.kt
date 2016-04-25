package pl.elpassion.eldebata.debate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import pl.elpassion.eldebata.R
import pl.elpassion.eldebata.base.BaseActivity

class VotingActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, VotingActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.voting_activity)
    }
}