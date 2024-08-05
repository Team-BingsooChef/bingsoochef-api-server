package bingsoochef.bingsoochef.bingsoo.presentation.res

data class BingsooDetailResponse (
    val bingsoo: BingsooDto,
    val toppings: List<ToppingAbstractDto>
)
