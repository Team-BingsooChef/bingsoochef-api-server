package bingsoochef.bingsoochef.bingsoo.application.command

import bingsoochef.bingsoochef.bingsoo.domain.Taste

data class CreateBingsooCommand (
    val userId: Long,
    val taste: Taste
)