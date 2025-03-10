package co.cata.entity

import jakarta.persistence.*

@Entity
@Table()
data class Asset (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id : Int?,
    val assetId : String,
    val description : String,
)
