package com.codeonblue.sample.service.impl

import com.codeonblue.sample.domain.Category
import com.codeonblue.sample.dto.CategoryDto
import com.codeonblue.sample.exception.ResourceNotFoundException
import com.codeonblue.sample.repository.CategoryRepository
import com.codeonblue.sample.service.CategoryService
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl(
    private val categoryRepository: CategoryRepository
) : CategoryService {

    override fun findById(id: Int): CategoryDto {
        val category = categoryRepository.findById(id)

        val categoryFound = category.orElseThrow {
            ResourceNotFoundException("Category not found.")
        }

        return categoryFound.toDto()
    }

    override fun findAll(): List<CategoryDto> {

        val categories = categoryRepository.findAll()

        return categories.map { category -> CategoryDto(category.id, category.description) }
    }

    override fun create(category: CategoryDto): CategoryDto {
        val categoryEntity = Category(description = category.description)
        return categoryRepository.save(categoryEntity).toDto()
    }

    override fun deleteById(id: Int) {
        val category = categoryRepository.findById(id)

        val categoryFound = category.orElseThrow {
            ResourceNotFoundException("Category not found.")
        }

        categoryRepository.delete(categoryFound)
    }
}
