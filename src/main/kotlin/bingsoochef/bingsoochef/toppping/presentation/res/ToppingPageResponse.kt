package bingsoochef.bingsoochef.toppping.presentation.res

import bingsoochef.bingsoochef.toppping.application.dto.ToppingPageInfo

data class ToppingPageResponse(
    val currPage: Int,
    val totalPages: Int,
    val totalElements: Long,
    val toppings: List<ToppingDto>
) {
    class ToppingDto(
        val toppingId: Long,
        val toppingTypeId: Long,
        val toppingTitle: String,
        val toppingPosition: Long,
        val isHidden: Boolean
    )

    companion object {
        fun from(toppingPageInfo: ToppingPageInfo): ToppingPageResponse {
            return ToppingPageResponse(
                currPage = toppingPageInfo.currPage,
                totalPages = toppingPageInfo.totalPages,
                totalElements = toppingPageInfo.totalElements,
                toppings = toppingPageInfo.toppings.map { ToppingDto(
                    toppingId = it.id,
                    toppingTypeId = it.toppingTypeId,
                    toppingTitle = it.title,
                    toppingPosition = it.position,
                    isHidden = it.isHidden
                ) }
            )
        }
    }
}