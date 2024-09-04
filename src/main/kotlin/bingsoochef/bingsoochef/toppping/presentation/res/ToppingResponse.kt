package bingsoochef.bingsoochef.toppping.presentation.res

import bingsoochef.bingsoochef.toppping.application.dto.CommentInfo
import bingsoochef.bingsoochef.toppping.application.dto.ToppingInfo
import java.time.LocalDateTime

data class ToppingResponse(
    val topping: ToppingDto,
    val comment: CommentDto?
) {
    class ToppingDto(
        val toppingId: Long,
        val chefId: Long,
        val bingsooId: Long,
        val toppingTypeId: Long,
        val chefName: String,
        val toppingTitle: String,
        val toppingContent: String,
        val toppingPosition: Long,
        val toppingCreatedTime: LocalDateTime
    )

    class CommentDto(
        val commentId: Long,
        val commentContent: String,
        val commentCreatedTime: LocalDateTime
    )

    companion object {
        fun of(topping: ToppingInfo, comment: CommentInfo?): ToppingResponse {
            return ToppingResponse(
                topping = ToppingDto(
                    toppingId = topping.toppingId,
                    chefId = topping.chefId,
                    bingsooId = topping.bingsooId,
                    toppingTypeId = topping.toppingTypeId,
                    chefName = topping.chefName,
                    toppingTitle = topping.title,
                    toppingContent = topping.content,
                    toppingPosition = topping.position,
                    toppingCreatedTime = topping.createdTime
                ),
                comment = if (comment != null)
                    CommentDto(
                        commentId = comment.id,
                        commentContent = comment.content,
                        commentCreatedTime = comment.createdTime
                    )
                    else null
            )
        }
    }
}
