package bingsoochef.bingsoochef.toppping.presentation.req

data class ToppingDto(
    val chefName: String,
    val toppingType: String,
    val toppingContent: String,
    val toppingPosition: Long
)
