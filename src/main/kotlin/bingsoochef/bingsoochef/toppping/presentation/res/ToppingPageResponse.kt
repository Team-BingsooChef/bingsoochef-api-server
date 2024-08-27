package bingsoochef.bingsoochef.toppping.presentation.res

data class ToppingPageResponse(
    val currPage: Int,
    val totalPage: Int,
    val toppings: List<ToppingAbstractDto>
)