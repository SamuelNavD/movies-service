package es.usj.androidapps.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User(
    @Id
    val id: UUID = UUID.randomUUID(),
    @Column(unique = true)
    val username: String,
    @Column
    val password: String
)