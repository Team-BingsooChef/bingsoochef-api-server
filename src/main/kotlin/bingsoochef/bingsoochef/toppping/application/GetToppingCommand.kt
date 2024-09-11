package bingsoochef.bingsoochef.toppping.application

data class GetToppingCommand(
    val userId: Long,
    val toppingId: Long
)