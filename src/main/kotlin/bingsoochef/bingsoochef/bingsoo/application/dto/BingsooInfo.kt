package bingsoochef.bingsoochef.bingsoo.application.dto

import bingsoochef.bingsoochef.bingsoo.domain.Bingsoo
import bingsoochef.bingsoochef.bingsoo.domain.Taste

data class BingsooInfo(
    var id: Long,
    var taste: Taste
) {
    companion object {
        fun from(bingsoo: Bingsoo): BingsooInfo {
            return BingsooInfo(
                id = bingsoo.id!!,
                taste = bingsoo.taste
            )
        }
    }
}