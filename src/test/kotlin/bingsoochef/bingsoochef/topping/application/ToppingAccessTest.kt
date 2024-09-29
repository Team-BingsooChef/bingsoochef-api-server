package bingsoochef.bingsoochef.topping.application

import bingsoochef.bingsoochef.bingsoo.domain.Bingsoo
import bingsoochef.bingsoochef.bingsoo.domain.Taste
import bingsoochef.bingsoochef.common.exception.BingsooException
import bingsoochef.bingsoochef.common.exception.code.ToppingError
import bingsoochef.bingsoochef.toppping.domain.Topping
import bingsoochef.bingsoochef.toppping.domain.ToppingType
import bingsoochef.bingsoochef.user.domain.AccountType
import bingsoochef.bingsoochef.user.domain.User
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class ToppingAccessTest : FunSpec({

    lateinit var bingsoo : Bingsoo
    lateinit var customer : User
    lateinit var chef : User
    lateinit var other : User
    lateinit var topping : Topping

    beforeSpec {
        bingsoo = Bingsoo(0L, Taste.STRAWBERRY)

        customer = User(0L, "customer", "pass", "customer", AccountType.GOOGLE, bingsoo)
        chef = User(1L, "chef", "pass", "chef", AccountType.GOOGLE, null)
        other = User(2L, "other", "pass", "other", AccountType.GOOGLE, null)

        topping = Topping(
            id = 0L,
            bingsoo = bingsoo,
            chef = chef,
            toppingType = ToppingType(0L, "인절미", "frozen-img", "defrosted-img"),
            comment = null,
            chefName = "홍길동",
            title = "테스트 할 토핑",
            content = "내용",
            position = 0L,
            createdTime = LocalDateTime.now(),
            isHidden = true
        )
    }

    context("빙수에 얼어 있는 토핑이 있을 때") {

        test("빙수가 얼어 있다.") {
            topping.isHidden shouldBe true
        }

        test("손님이 접근하면 403 - 빙수가 얼어 있음 예외가 발생한다") {
            val exception = shouldThrow<BingsooException> {
                topping.isReadableBy(customer)
            }
            exception.errorCode shouldBe ToppingError.TOPPING_UNFROZEN
        }

        test("셰프는 접근할 수 있다.") {
            shouldNotThrow<BingsooException> {
                topping.isReadableBy(chef)
            }
        }

        test("제 3자가 접근하면 403 - 권한 없음 예외가 발생한다") {
            val exception = shouldThrow<BingsooException> {
                topping.isReadableBy(other)
            }
            exception.errorCode shouldBe ToppingError.TOPPING_FORBIDDEN
        }
    }

    context("빙수에 녹은 토핑이 있을 때") {
        topping.defrost()

        test("빙수가 녹아 있다.") {
            topping.isHidden shouldBe false
        }

        test("손님은 접근할 수 있다.") {
            shouldNotThrow<BingsooException> {
                topping.isReadableBy(customer)
            }
        }

        test("셰프는 접근할 수 있다.다") {
            shouldNotThrow<BingsooException> {
                topping.isReadableBy(chef)
            }
        }

        test("제 3자가 접근하면 403 - 권한 없음 예외가 발생한다") {
            val exception = shouldThrow<BingsooException> {
                topping.isReadableBy(other)
            }
            exception.errorCode shouldBe ToppingError.TOPPING_FORBIDDEN
        }
    }
})
