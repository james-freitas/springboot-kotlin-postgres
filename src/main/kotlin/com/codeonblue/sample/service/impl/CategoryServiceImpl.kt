package com.codeonblue.sample.service.impl

import com.codeonblue.sample.dto.CategoryDto
import com.codeonblue.sample.exception.ResourceNotFoundException
import com.codeonblue.sample.repository.CategoryRepository
import com.codeonblue.sample.service.CategoryService
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl(
    private val categoryRepository: CategoryRepository
) : CategoryService {

    override fun getCategoryById(id: Int): CategoryDto {
        val category = categoryRepository.findById(id)

        val categoryFound = category.orElseThrow {
            ResourceNotFoundException("Category not found.")
        }

        return categoryFound.toDto()
    }
}
