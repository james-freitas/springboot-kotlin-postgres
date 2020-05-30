package com.codeonblue.sample.service

import com.codeonblue.sample.dto.CategoryDto

interface CategoryService {

    fun getCategoryById(id: Int): CategoryDto
}
