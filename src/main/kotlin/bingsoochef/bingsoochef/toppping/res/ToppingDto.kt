package bingsoochef.bingsoochef.toppping.res

import java.time.LocalDateTime

data class ToppingDto (
    val toppingId: Long,
    val chefId: Long,
    val bingsooId: Long,
    val toppingTypeId: Long,
    val chefName: String,
    val toppingTitle: String,
    val toppingContent: String,
    val toppingPosition: Long,
    val createdTime: LocalDateTime
)