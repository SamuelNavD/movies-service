package es.usj.androidapps.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class AppUser(
    @Id
    @GeneratedValue
    val id: Long,
    @Column(unique = true)
    val username: String,
    @Column
    val password: String
)