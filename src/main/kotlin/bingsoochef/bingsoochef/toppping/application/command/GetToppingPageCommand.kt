package bingsoochef.bingsoochef.toppping.application.command

import org.springframework.data.domain.Pageable

data class GetToppingPageCommand(
    val bingsooId: Long,
    val pageable: Pageable
)