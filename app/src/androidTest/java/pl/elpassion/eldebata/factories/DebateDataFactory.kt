package pl.elpassion.eldebata.factories

import pl.elpassion.eldebata.debate.api.Answer
import pl.elpassion.eldebata.debate.api.Answers
import pl.elpassion.eldebata.debate.api.DebateData

object DebateDataFactory {

    val debateTopic = "Czy luzne podejscie do pracy grozi sukcesu firmy?"
    val positiveAnswer = "Grozi"
    val negativeAnswer = "Nie grozi"
    val neutralAnswer = "Nie wiem"
    val positiveVote = Answer(1, positiveAnswer)
    val negativeVote = Answer(2, negativeAnswer)
    val neutralVote = Answer(4, neutralAnswer)

    fun newDebateData(): DebateData {
        return DebateData(topic = debateTopic, answers = Answers(
                positive = positiveVote,
                negative = negativeVote,
                neutral = neutralVote
        ))
    }
}