package com.codeonblue.sample.controller

import com.codeonblue.sample.dto.CategoryDto
import com.codeonblue.sample.service.CategoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/categories")
class CategoryController(
    private val categoryService: CategoryService
) {

    @GetMapping("/{id}")
    fun getCategory(@PathVariable id: Int): ResponseEntity<CategoryDto?>? {
        return ResponseEntity<CategoryDto?>(
            categoryService.findById(id), HttpStatus.OK
        )
    }

    @GetMapping
    fun getCategories(): ResponseEntity<List<CategoryDto>> {
        return ResponseEntity<List<CategoryDto>>(
            categoryService.findAll(), HttpStatus.OK
        )
    }
}
