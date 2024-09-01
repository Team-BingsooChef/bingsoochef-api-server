package bingsoochef.bingsoochef.bingsoo.application

import bingsoochef.bingsoochef.bingsoo.domain.Taste

data class UpdateBingsooCommand(
    val userId: Long,
    val taste: Taste
)