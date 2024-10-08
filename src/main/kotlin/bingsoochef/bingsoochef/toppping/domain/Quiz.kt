package bingsoochef.bingsoochef.toppping.domain

import bingsoochef.bingsoochef.common.exception.BingsooException
import bingsoochef.bingsoochef.common.exception.code.ToppingError
import bingsoochef.bingsoochef.user.domain.User
import jakarta.persistence.*

@Table(name = "quiz")
@Entity
class Quiz(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topping_id")
    var topping: Topping,

    @Column(name = "quiz_title")
    var title: String,
    @Enumerated(EnumType.STRING)
    var quizType: QuizType,
    var wrongCount: Short = 0
) {
    fun isReadableBy(user: User) {
        if (topping.chef == user || user.bingsoo == topping.bingsoo)
            return

        throw BingsooException(ToppingError.QUIZ_FORBIDDEN)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Topping) return false
        if (this === other) return true

        if (id == null || other.id == null) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}