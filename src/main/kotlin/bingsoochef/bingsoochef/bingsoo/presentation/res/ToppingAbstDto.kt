package bingsoochef.bingsoochef.bingsoo.presentation.res

data class ToppingAbstDto (
    val toppingId: Long,
    val toppingTypeId: Long,
    val toppingTitle: String,
    val toppingPosition: Long,
    val isHidden: Boolean
)