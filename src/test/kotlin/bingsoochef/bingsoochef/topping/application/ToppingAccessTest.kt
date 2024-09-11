package bingsoochef.bingsoochef.topping.application

import bingsoochef.bingsoochef.bingsoo.domain.Bingsoo
import bingsoochef.bingsoochef.bingsoo.domain.Taste
import bingsoochef.bingsoochef.global.error.ForbiddenException
import bingsoochef.bingsoochef.toppping.domain.Topping
import bingsoochef.bingsoochef.toppping.domain.ToppingType
import bingsoochef.bingsoochef.user.domain.User
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import java.time.LocalDateTime

class ToppingAccessTest : FunSpec({

    lateinit var bingsoo : Bingsoo
    lateinit var customer : User
    lateinit var chef : User
    lateinit var other : User
    lateinit var topping : Topping

    beforeSpec {
        bingsoo = Bingsoo(0L, Taste.STRAWBERRY)

        customer = User(0L, bingsoo)
        chef = User(0L, null)
        other = User(0L, null)

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
            val exception = shouldThrow<ForbiddenException> {
                topping.isReadableBy(customer)
            }
            exception.message shouldContain "요청한 토핑이 아직 녹지 않았습니다."
        }

        test("셰프는 접근할 수 있다.") {
            shouldNotThrow<ForbiddenException> {
                topping.isReadableBy(chef)
            }
        }

        test("제 3자가 접근하면 403 - 권한 없음 예외가 발생한다") {
            val exception = shouldThrow<ForbiddenException> {
                topping.isReadableBy(other)
            }
            exception.message shouldContain "사용자 ${other.userId}은(는) 요청한 토핑에 접근할 수 없습니다."
        }
    }

    context("빙수에 녹은 토핑이 있을 때") {
        topping.defrost()

        test("빙수가 녹아 있다.") {
            topping.isHidden shouldBe false
        }

        test("손님은 접근할 수 있다.") {
            shouldNotThrow<ForbiddenException> {
                topping.isReadableBy(customer)
            }
        }

        test("셰프는 접근할 수 있다.다") {
            shouldNotThrow<ForbiddenException     > {
                topping.isReadableBy(chef)
            }
        }

        test("제 3자가 접근하면 403 - 권한 없음 예외가 발생한다") {
            val exception = shouldThrow<ForbiddenException> {
                topping.isReadableBy(other)
            }
            exception.message shouldContain "사용자 ${other.userId}은(는) 요청한 토핑에 접근할 수 없습니다."
        }
    }
})
