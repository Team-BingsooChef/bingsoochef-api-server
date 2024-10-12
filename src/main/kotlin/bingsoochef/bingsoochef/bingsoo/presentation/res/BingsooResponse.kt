package bingsoochef.bingsoochef.bingsoo.presentation.res

import bingsoochef.bingsoochef.bingsoo.application.dto.BingsooInfo
import bingsoochef.bingsoochef.bingsoo.domain.Taste

data class BingsooResponse(
    val bingsoo: BingsooDto
) {
    class BingsooDto(
        val bingsooId: Long,
        val taste: Taste
    )

    companion object {
        fun from(info: BingsooInfo): BingsooResponse {
            return BingsooResponse(
                BingsooDto(
                    bingsooId = info.id,
                    taste = info.taste
                )
            )
        }
    }
}
