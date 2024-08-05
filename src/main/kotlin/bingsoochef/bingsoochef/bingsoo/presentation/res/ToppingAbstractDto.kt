package bingsoochef.bingsoochef.bingsoo.presentation.res

data class ToppingAbstractDto (
    val toppingId: Long,
    val toppingTypeId: Long,
    val toppingTitle: String,
    val toppingPosition: Long,
    val isHidden: Boolean
)