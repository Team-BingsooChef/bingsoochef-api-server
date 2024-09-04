package bingsoochef.bingsoochef.toppping.application.dto

import bingsoochef.bingsoochef.toppping.domain.Topping
import java.time.LocalDateTime

data class ToppingInfo (
    val toppingId: Long,
    val chefId: Long,
    val bingsooId: Long,
    val toppingTypeId: Long,
    val chefName: String,
    val title: String,
    val content: String,
    val position: Long,
    val createdTime: LocalDateTime
) {
    companion object {
        fun from(topping: Topping): ToppingInfo {
            return ToppingInfo(
                toppingId = topping.id!!,
                chefId = topping.chef.userId!!,
                bingsooId = topping.bingsoo.id!!,
                toppingTypeId = topping.toppingType.id!!,
                chefName = topping.chefName,
                title = topping.title,
                content = topping.content,
                position = topping.position,
                createdTime = topping.createdTime
            )
        }
    }
}