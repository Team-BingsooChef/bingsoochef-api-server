package bingsoochef.bingsoochef.bingsoo.presentation.res

import bingsoochef.bingsoochef.bingsoo.domain.Bingsoo
import bingsoochef.bingsoochef.bingsoo.domain.Taste

data class BingsooDto(
    val bingsooId: Long,
    val taste: Taste
) {
    companion object {
        fun from(bingsoo: Bingsoo): BingsooDto {
            return BingsooDto(bingsoo.id!!, bingsoo.taste)
        }
    }
}
