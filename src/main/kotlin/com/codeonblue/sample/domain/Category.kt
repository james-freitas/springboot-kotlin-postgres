package com.codeonblue.sample.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator

@Entity
class Category(
    @Id
    @SequenceGenerator(
        name = "category_id_seq",
        sequenceName = "category_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "category_id_seq"
    )
    @Column(updatable = false)
    var id: Int? = null,
    var description: String
)
