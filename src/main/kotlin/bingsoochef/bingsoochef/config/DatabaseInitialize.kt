package bingsoochef.bingsoochef.config

import bingsoochef.bingsoochef.toppping.ToppingTypeRepository
import bingsoochef.bingsoochef.toppping.domain.ToppingType
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class DatabaseInitialize(val toppingTypeRepository : ToppingTypeRepository) {

    @PostConstruct
    fun init() {
        if (toppingTypeRepository.count() != 0L) {
            logger.info { "데이터베이스에 값이 존재하여 데이터 초기화 작업을 수행하지 않습니다." }
            return
        }

        var toppingTypes : MutableList<ToppingType> = mutableListOf()
        toppingTypes.add(ToppingType(null, "딸기", "https://url.kr/5fzlnn", "https://url.kr/twyun3"))
        toppingTypes.add(ToppingType(null, "키위", "https://url.kr/iii52y", "https://url.kr/giez4f"))

        toppingTypeRepository.saveAll(toppingTypes)
    }
}