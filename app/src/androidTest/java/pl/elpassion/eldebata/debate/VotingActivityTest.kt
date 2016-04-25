package pl.elpassion.eldebata.debate

import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import pl.elpassion.eldebata.R
import pl.elpassion.eldebata.common.hasText
import pl.elpassion.eldebata.common.onId
import pl.elpassion.eldebata.common.rule
import pl.elpassion.eldebata.debate.api.DebateApi
import pl.elpassion.eldebata.debate.api.DebateApiProvider
import pl.elpassion.eldebata.factories.DebateDataFactory.debateTopic
import pl.elpassion.eldebata.factories.DebateDataFactory.newDebateData
import pl.elpassion.eldebata.factories.DebateDataFactory.positiveAnswer
import rx.Observable
import org.mockito.Mockito.`when` as on

class VotingActivityTest {

    val api = Mockito.mock(DebateApi::class.java)

    @JvmField @Rule
    val rule = rule<VotingActivity> {
        on(api.getDebateData()).thenReturn(Observable.just(newDebateData()))
        DebateApiProvider.override = api
    }

    @Test
    fun apiShouldBeCalledAtTheActivityStart() {
        verify(api, times(1)).getDebateData()
    }

    @Test
    fun shouldHaveCorrectTopicAtTheStart() {
        onId(R.id.voting_activity_debate_topic).hasText(debateTopic)
    }

    @Test
    fun shouldHavePositiveVoteButtonWithCorrectName() {
        onId(R.id.voting_activity_positive_vote_button).hasText(positiveAnswer)
    }
}