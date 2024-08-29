package bingsoochef.bingsoochef.bingsoo.persistence

import bingsoochef.bingsoochef.bingsoo.domain.Bingsoo
import bingsoochef.bingsoochef.bingsoo.domain.Taste
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JpaRepositoryTest @Autowired constructor(
    private var bingsooRepository : BingsooRepository
) : BehaviorSpec({

    Given("ID가 없는 빙수가 주어지고") {
        val givenBingsoo = Bingsoo(null, Taste.valueOf("STRAWBERRY"))

        When("빙수를 저장해 ID가 생겼을 때") {
            val resultId = bingsooRepository.save(givenBingsoo).id

            Then("빙수를 다시 조회할 수 있다.") {
                bingsooRepository.findByIdOrNull(resultId) shouldNotBe null
            }
        }
    }

    Given("새로운 빙수를 저장하고") {
        val givenBingsoo = Bingsoo(null, Taste.valueOf("STRAWBERRY"))
        val savedBingsoo = bingsooRepository.save(givenBingsoo)

        When("빙수를 다시 조회했을 때") {
            val foundedBingsoo = bingsooRepository.findByIdOrNull(savedBingsoo.id)!!

            Then("equals의 reflexive 테스트") {
                givenBingsoo.equals(givenBingsoo) shouldBe true
                savedBingsoo.equals(savedBingsoo) shouldBe true
                foundedBingsoo.equals(foundedBingsoo) shouldBe true
            }
            Then("equals의 symmetric 테스트") {
                givenBingsoo.equals(savedBingsoo) shouldBe true
                savedBingsoo.equals(givenBingsoo) shouldBe true

                givenBingsoo.equals(foundedBingsoo) shouldBe true
                foundedBingsoo.equals(givenBingsoo) shouldBe true

                savedBingsoo.equals(foundedBingsoo) shouldBe true
                foundedBingsoo.equals(savedBingsoo) shouldBe true
            }
            Then("equals의 transitive 테스트") {
                givenBingsoo.equals(savedBingsoo) shouldBe true
                savedBingsoo.equals(foundedBingsoo) shouldBe true
                foundedBingsoo.equals(givenBingsoo) shouldBe true
            }
            Then("equals의 consistent 테스트") {
                givenBingsoo.equals(foundedBingsoo) shouldBe true
                savedBingsoo.equals(foundedBingsoo) shouldBe true

                foundedBingsoo.taste = Taste.valueOf("CHOCO")

                givenBingsoo.equals(foundedBingsoo) shouldBe true
                savedBingsoo.equals(foundedBingsoo) shouldBe true
            }
        }
    }
})