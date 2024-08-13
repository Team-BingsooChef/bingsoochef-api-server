package bingsoochef.bingsoochef.toppping.domain

import jakarta.persistence.*

@Table(name = "topping_type")
@Entity
class ToppingType(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topping_type_id")
    val id: Long,

    val toppingName: String,
    val toppingImg: String
)
