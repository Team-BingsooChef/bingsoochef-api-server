package bingsoochef.bingsoochef.bingsoo.persistence

import bingsoochef.bingsoochef.bingsoo.domain.Bingsoo
import bingsoochef.bingsoochef.bingsoo.domain.Taste
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JpaRepositoryTest(
    private var bingsooRepository : BingsooRepository
) : BehaviorSpec({

    Given("ID가 없는 빙수가 주어지고") {
        var givenBingsoo = Bingsoo(null, Taste.valueOf("STRAWBERRY"))

        When("빙수를 저장했을 때") {
            var resultBingsoo = bingsooRepository.save(givenBingsoo)

            Then("ID를 가지는 주어진 정보의 빙수가 생성된다.") {
                resultBingsoo.id shouldNotBe null
                resultBingsoo.taste shouldBe givenBingsoo.taste
            }
        }
    }
})