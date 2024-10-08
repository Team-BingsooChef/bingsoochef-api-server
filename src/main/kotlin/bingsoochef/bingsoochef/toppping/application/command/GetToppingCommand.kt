package bingsoochef.bingsoochef.toppping.application.command

data class GetToppingCommand(
    val userId: Long,
    val toppingId: Long
)