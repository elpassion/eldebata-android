package pl.elpassion.eldebata.debate

import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import pl.elpassion.eldebata.R
import pl.elpassion.eldebata.common.click
import pl.elpassion.eldebata.common.hasText
import pl.elpassion.eldebata.common.onId
import pl.elpassion.eldebata.common.rule
import pl.elpassion.eldebata.debate.api.*
import pl.elpassion.eldebata.factories.DebateDataFactory.debateTopic
import pl.elpassion.eldebata.factories.DebateDataFactory.negativeAnswer
import pl.elpassion.eldebata.factories.DebateDataFactory.negativeVote
import pl.elpassion.eldebata.factories.DebateDataFactory.neutralAnswer
import pl.elpassion.eldebata.factories.DebateDataFactory.neutralVote
import pl.elpassion.eldebata.factories.DebateDataFactory.newDebateData
import pl.elpassion.eldebata.factories.DebateDataFactory.positiveAnswer
import pl.elpassion.eldebata.factories.DebateDataFactory.positiveVote
import pl.elpassion.eldebata.prefs.AuthToken
import rx.Observable
import org.mockito.Mockito.`when` as on

class VotingActivityTest {

    val debateDataApi = Mockito.mock(DebateApi::class.java)
    val voteApi = Mockito.mock(VoteApi::class.java)

    @JvmField @Rule
    val rule = rule<VotingActivity> {
        AuthToken.save("token")
        on(debateDataApi.getDebateData("token")).thenReturn(Observable.just(newDebateData()))
        DebateApiProvider.override = debateDataApi
        VoteApiProvider.override = voteApi
    }

    @Test
    fun apiShouldBeCalledAtTheActivityStart() {
        verify(debateDataApi, times(1)).getDebateData("token")
    }

    @Test
    fun shouldHaveCorrectTopicAtTheStart() {
        onId(R.id.voting_activity_debate_topic).hasText(debateTopic)
    }

    @Test
    fun shouldHavePositiveVoteButtonWithCorrectName() {
        onId(R.id.voting_activity_positive_vote_button).hasText(positiveAnswer)
    }

    @Test
    fun shouldHaveNegativeVoteButtonWithCorrectName() {
        onId(R.id.voting_activity_negative_vote_button).hasText(negativeAnswer)
    }

    @Test
    fun shouldHaveNeutralVoteButtonWithCorrectName() {
        onId(R.id.voting_activity_neutral_vote_button).hasText(neutralAnswer)
    }
    
    @Test
    fun shouldMakeCallToApiWithCorrectAnswerWhenPositiveButtonIsClicked() {
        clickAndVerifyApiCall(R.id.voting_activity_positive_vote_button, positiveVote)
    }

    @Test
    fun shouldMakeCallToApiWithCorrectAnswerWhenNegativeButtonIsClicked() {
        clickAndVerifyApiCall(R.id.voting_activity_negative_vote_button, negativeVote)
    }

    @Test
    fun shouldMakeCallToApiWithCorrectAnswerWhenNeutralButtonIsClicked() {
        clickAndVerifyApiCall(R.id.voting_activity_neutral_vote_button, neutralVote)
    }

    private fun clickAndVerifyApiCall(voteButtonId: Int, answer: Answer) {
        onId(voteButtonId).click()
        verify(voteApi, times(1)).vote("token", answer)
    }

}