package bingsoochef.bingsoochef.toppping.application.dto

import bingsoochef.bingsoochef.toppping.domain.Quiz

data class TryResultInfo(
    val result: Boolean,
    val wrongCount: Short
) {
    companion object {
        fun of(result: Boolean, quiz: Quiz): TryResultInfo {
            return TryResultInfo(
                result = result,
                wrongCount = quiz.wrongCount
            )
        }
    }
}