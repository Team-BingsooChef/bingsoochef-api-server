package bingsoochef.bingsoochef.bingsoo.presentation.req

import bingsoochef.bingsoochef.bingsoo.domain.Taste

data class CreateBingsooRequest (
    val taste: Taste,
    val userId: Long
)