package bingsoochef.bingsoochef.topping.application

import bingsoochef.bingsoochef.toppping.application.GetToppingPageCommand
import bingsoochef.bingsoochef.toppping.application.ToppingService
import bingsoochef.bingsoochef.toppping.application.dto.ToppingPageInfo
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.ints.shouldBeLessThan
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ToppingPageTest(
    @Autowired
    private val toppingService: ToppingService
) : BehaviorSpec({

    Given("범위 내의 토핑 페이지 조회 입력이 주어지고") {
        val pageable : Pageable = PageRequest.of(0, 8)
        val bingsooId = 1L
        val command = GetToppingPageCommand(
            bingsooId,
            pageable
        )

        When("토핑 페이지를 조회할 경우") {
            val result : ToppingPageInfo = toppingService.getToppingPage(command)

            Then("정상적으로 페이지를 조회한다.") {
                result.currPage shouldBeLessThan result.totalPages
                result.toppings.size shouldBeInRange IntRange(0, 8)
            }
        }
    }

    Given("범위 밖의 토핑 페이지 조회 입력이 주어지고") {
        val pageable : Pageable = PageRequest.of(9000, 8)
        val bingsooId = 1L
        val command = GetToppingPageCommand(
            bingsooId,
            pageable
        )

        When("토핑 페이지를 조회할 경우") {
            val result : ToppingPageInfo = toppingService.getToppingPage(command)

            Then("toppings가 빈 배열로 반환된다.") {
                result.toppings.size shouldBeExactly 0
            }
        }
    }
})