package bingsoochef.bingsoochef.toppping.application.dto

import bingsoochef.bingsoochef.toppping.domain.Question

class QuestionInfo (
    val id: Long,
    val content: String,
    val isAnswer: Boolean
) {
    companion object {
        fun from(question: Question): QuestionInfo {
            return QuestionInfo(question.id!!, question.content, question.isAnswer)
        }
    }
}