package pl.elpassion.eldebata.factories

import pl.elpassion.eldebata.debate.api.Answer
import pl.elpassion.eldebata.debate.api.Answers
import pl.elpassion.eldebata.debate.api.DebateData

object DebateDataFactory {

    val debateTopic = "Czy luzne podejscie do pracy grozi sukcesu firmy?"
    val positiveAnswer = "Grozi"
    val negativeAnswer = "Nie grozi"

    fun newDebateData(): DebateData {
        return DebateData(topic = debateTopic, answers = Answers(
                positive = Answer(1, positiveAnswer),
                negative = Answer(2, negativeAnswer),
                neutral = Answer(4, "Nie wiem")
        ))
    }
}