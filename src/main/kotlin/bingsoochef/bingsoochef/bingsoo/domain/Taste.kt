package bingsoochef.bingsoochef.bingsoo.domain

import com.fasterxml.jackson.annotation.JsonCreator

enum class Taste {
    STRAWBERRY, CHOCO, CONDENSED_MILK, MATCHA, MANGO, MINT_CHOCO;

//    companion object {
//        @JsonCreator
//        @JvmStatic
//        fun fromValue(value: String): Taste {
//            return entries.find { it.name.equals(value, ignoreCase = true) }
//                ?: throw IllegalArgumentException("'$value'는 '$entries' 중에 존재하지 않습니다.")
//        }
//    }
}