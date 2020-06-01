package com.codeonblue.sample.service.impl

import com.codeonblue.sample.domain.Category
import com.codeonblue.sample.exception.ResourceNotFoundException
import com.codeonblue.sample.repository.CategoryRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class CategoryServiceImplTest {

    @MockK
    private lateinit var categoryRepository: CategoryRepository

    @InjectMockKs
    private lateinit var categoryService: CategoryServiceImpl

    @Test
    fun `Should find existent category by Id successfully`() {

        val category = Category(
            id = 1,
            description = "Test description"
        )

        every { categoryRepository.findById(1) } returns Optional.of(category)

        val categoryFound = categoryService.findById(1)

        assertThat(categoryFound.id).isEqualTo(category.id)
        assertThat(categoryFound.description).isEqualTo(category.description)
        verify(exactly = 1) { categoryRepository.findById(1) }
    }

    @Test
    fun `Should throw execption when trying to find not existent category`() {

        every { categoryRepository.findById(any()) } returns Optional.empty()

        assertThrows<ResourceNotFoundException> { categoryService.findById(1) }

        verify(exactly = 1) { categoryRepository.findById(1) }
    }

    @Test
    fun `Should return a non empty category list`() {

        val categoryList = listOf(
            Category(
                id = 1,
                description = "Category 1"
            ),
            Category(
                id = 2,
                description = "Category 2"
            )
        )

        every { categoryRepository.findAll() } returns categoryList

        val categoryListFound = categoryService.findAll()

        assertThat(categoryListFound.size).isEqualTo(2)
        assertThat(categoryListFound[0].description).isEqualTo(categoryList[0].description)
        assertThat(categoryListFound[1].description).isEqualTo(categoryList[1].description)

        verify(exactly = 1) { categoryRepository.findAll() }
    }

    @Test
    fun `Should return an empty category list`() {

        every { categoryRepository.findAll() } returns emptyList()

        val categoryListFound = categoryService.findAll()

        assertThat(categoryListFound.size).isEqualTo(0)

        verify(exactly = 1) { categoryRepository.findAll() }
    }
}
