package bingsoochef.bingsoochef.toppping.presentation.res

import bingsoochef.bingsoochef.toppping.application.dto.TryResultInfo

data class TryResultResponse(
    val result: Boolean,
    val wrongCount: Short
) {
    companion object {
        fun from(info: TryResultInfo): TryResultResponse {
            return TryResultResponse(info.result, info.wrongCount)
        }
    }
}
