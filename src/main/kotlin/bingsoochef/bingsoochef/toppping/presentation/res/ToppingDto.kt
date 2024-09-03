package bingsoochef.bingsoochef.toppping.presentation.res

import bingsoochef.bingsoochef.toppping.domain.Topping
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
    val toppingCreatedTime: LocalDateTime
) {

    companion object {
        fun from(topping : Topping): ToppingDto {
            return ToppingDto(
                toppingId = topping.id!!,
                chefId = topping.chef.userId!!,
                bingsooId = topping.bingsoo.id!!,
                toppingTypeId = topping.toppingType.id!!,
                chefName = topping.chefName,
                toppingTitle = topping.title,
                toppingContent = topping.content,
                toppingPosition = topping.position,
                toppingCreatedTime = topping.createdTime
            )
        }
    }
}