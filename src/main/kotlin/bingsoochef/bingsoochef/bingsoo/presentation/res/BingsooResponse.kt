package bingsoochef.bingsoochef.bingsoo.presentation.res

data class BingsooResponse(
    val bingsoo: BingsooDto
) {
    companion object {
        fun of(bingsooDto: BingsooDto): BingsooResponse {
            return BingsooResponse(bingsooDto)
        }
    }
}
