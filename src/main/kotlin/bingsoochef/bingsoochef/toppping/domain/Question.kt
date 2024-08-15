package bingsoochef.bingsoochef.toppping.domain

import jakarta.persistence.*

@Table(name = "question")
@Entity
class Question(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    var quiz: Quiz,

    @Column(name = "question_content")
    var content: String,
    var isAnswer: Boolean = false
) {
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