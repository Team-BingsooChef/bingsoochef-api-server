package bingsoochef.bingsoochef.topping.application

import bingsoochef.bingsoochef.toppping.application.GetToppingPageCommand
import bingsoochef.bingsoochef.toppping.application.ToppingService
import bingsoochef.bingsoochef.toppping.application.dto.ToppingPageInfo
import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.ints.shouldBeLessThan
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.jdbc.datasource.init.ScriptUtils
import org.springframework.test.context.jdbc.Sql
import java.sql.SQLException
import javax.sql.DataSource

private val logger = KotlinLogging.logger {}

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/sql/topping-setup.sql")
class ToppingPageTest(
    @Autowired private val toppingService: ToppingService,
    @Autowired  private val dataSource: DataSource
) : BehaviorSpec({

    beforeSpec {
        try {
            dataSource.connection.use {conn ->
                ScriptUtils.executeSqlScript(
                    conn,
                    ClassPathResource("/sql/topping-setup.sql")
                )
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    Given("범위 내의 토핑 페이지 조회 입력이 주어지고") {
        val pageable : Pageable = PageRequest.of(0, 8)
        val bingsooId = 50L
        val command = GetToppingPageCommand(
            bingsooId,
            pageable
        )

        When("토핑 페이지를 조회할 경우") {
            val info : ToppingPageInfo = toppingService.getToppingPage(command)

            Then("정상적으로 페이지를 조회한다.") {
                info.currPage shouldBeLessThan info.totalPages
                info.toppings.size shouldBeInRange IntRange(0, 8)
            }
        }
    }

    Given("범위 밖의 토핑 페이지 조회 입력이 주어지고") {
        val pageable : Pageable = PageRequest.of(9000, 8)
        val bingsooId = 50L
        val command = GetToppingPageCommand(
            bingsooId,
            pageable
        )

        When("토핑 페이지를 조회할 경우") {
            val info : ToppingPageInfo = toppingService.getToppingPage(command)

            Then("toppings가 빈 배열로 반환된다.") {
                info.toppings.size shouldBeExactly 0
            }
        }
    }
})