package br.com.ms.moipchallenge.models

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id

@Entity
@JsonPropertyOrder("id", "name")
class Client(
        identification: Long
) {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0

    @Column(nullable = false, unique = true)
    val name = "Client $identification"
}