package com.codeonblue.sample.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Category(
    @Id @GeneratedValue var id: Long? = null,
    var description: String
)