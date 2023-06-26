package com.example.composition.data

import com.example.composition.domain.entity.GameSettings
import com.example.composition.domain.entity.Level
import com.example.composition.domain.entity.Question
import com.example.composition.domain.repository.GameRepository
import javax.inject.Inject
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class GameRepositoryImpl @Inject constructor() : GameRepository {

    companion object {
        private const val MIN_SUM_VALUE = 2
        private const val MIN_ANSWER_VALUE = 1
    }

    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        val from = max(rightAnswer - countOfOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue, rightAnswer + countOfOptions)
        while(options.size < countOfOptions) {
            options.add(Random.nextInt(from, to))
        }
        return Question(sum, visibleNumber, options.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> GameSettings(
                10,
                3,
                50,
                10
            )
            Level.EASY -> GameSettings(
                20,
                5,
                60,
                20
            )
            Level.MIDDLE -> GameSettings(
                40,
                15,
                70,
                40
            )
            Level.HARD -> GameSettings(
                60,
                30,
                85,
                120
            )
        }
    }
}