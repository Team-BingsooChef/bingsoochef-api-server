package bingsoochef.bingsoochef.toppping.application.dto

import bingsoochef.bingsoochef.toppping.domain.Topping
import org.springframework.data.domain.Page

data class ToppingPageInfo (
    val currPage: Int,
    val totalPages: Int,
    val totalElements: Long,
    val toppings: List<ToppingDto>
) {
    class ToppingDto(
        val id: Long,
        val toppingTypeId: Long,
        val title: String,
        val position: Long,
        val isHidden: Boolean
    )

    companion object {
        fun from(page: Page<Topping>): ToppingPageInfo {
            return ToppingPageInfo(
                    currPage = page.number,
                    totalPages = page.totalPages,
                    totalElements = page.totalElements,
                    toppings = page.content.map {
                        ToppingDto(
                            id = it.id!!,
                            toppingTypeId = it.toppingType.id!!,
                            title = it.title,
                            position = it.position,
                            isHidden = it.isHidden
                        )
                    }
            )
        }
    }
}