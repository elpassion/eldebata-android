package pl.elpassion.eldebata.debate

import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import pl.elpassion.eldebata.R
import pl.elpassion.eldebata.common.hasText
import pl.elpassion.eldebata.common.onId
import pl.elpassion.eldebata.common.rule
import pl.elpassion.eldebata.debate.api.*
import rx.Observable
import org.mockito.Mockito.`when` as on

class VotingActivityTest {

    val api = Mockito.mock(DebateApi::class.java)
    val debateData = DebateData(topic = "Czy luzne podejscie do pracy grozi sukcesu firmy?", answers = Answers(
            positive = Answer(1, "Grozi"),
            negative = Answer(2, "Nie grozi"),
            neutral = Answer(4, "Nie wiem")
    ))

    @JvmField @Rule
    val rule = rule<VotingActivity> {
        on(api.getDebateData()).thenReturn(Observable.just(debateData))
        DebateApiProvider.override = api
    }

    @Test
    fun shouldHaveCorrectTopicAtTheStart() {
        onId(R.id.voting_activity_debate_topic).hasText(debateData.topic)
    }
}