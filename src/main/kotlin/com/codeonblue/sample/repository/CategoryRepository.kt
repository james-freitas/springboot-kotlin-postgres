package com.codeonblue.sample.repository

import com.codeonblue.sample.domain.Category
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : CrudRepository<Category, Int>
