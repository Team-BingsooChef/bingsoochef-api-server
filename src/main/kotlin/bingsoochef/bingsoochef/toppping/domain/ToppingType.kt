package bingsoochef.bingsoochef.toppping.domain

import jakarta.persistence.*

@Table(name = "topping_type")
@Entity
class ToppingType(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topping_type_id")
    val id: Long? = null,

    @Column(name = "topping_type_name")
    val name: String,
    val frozenImg: String,
    val defrostedImg: String
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
