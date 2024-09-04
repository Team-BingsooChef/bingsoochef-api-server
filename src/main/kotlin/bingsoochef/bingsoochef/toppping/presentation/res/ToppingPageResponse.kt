package bingsoochef.bingsoochef.toppping.presentation.res

data class ToppingPageResponse(
    val currPage: Int,
    val totalPage: Int,
    val totalElements: Int,
    val toppings: List<ToppingDto>
) {
    class ToppingDto(
        val toppingId: Long,
        val toppingTypeId: Long,
        val toppingTitle: String,
        val toppingPosition: Long,
        val isHidden: Boolean
    )
}