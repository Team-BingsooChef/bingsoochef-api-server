package bingsoochef.bingsoochef.bingsoo.presentation.res

import bingsoochef.bingsoochef.bingsoo.domain.Taste

data class BingsooDto(
    val bingsooId: Long,
    val customerId: Long,
    val taste: Taste
)
