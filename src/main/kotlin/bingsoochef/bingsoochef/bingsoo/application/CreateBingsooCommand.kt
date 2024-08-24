package bingsoochef.bingsoochef.bingsoo.application

import bingsoochef.bingsoochef.bingsoo.domain.Taste

data class CreateBingsooCommand (
    val userId: Long,
    val taste: Taste
) {
}