package com.codeonblue.sample.service

import com.codeonblue.sample.dto.CategoryDto

interface CategoryService {

    fun findById(id: Int): CategoryDto?

    fun findAll(): List<CategoryDto>
}
