package com.codeonblue.sample.service.impl

import com.codeonblue.sample.domain.Category
import com.codeonblue.sample.repository.CategoryRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.lang.RuntimeException
import java.util.Optional


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

        val categoryFound = categoryService.getCategoryById(1)

        assertThat(categoryFound.id).isEqualTo(category.id)
        assertThat(categoryFound.description).isEqualTo(category.description)
        verify(exactly = 1) { categoryRepository.findById(1) }
    }

    @Test
    fun `Should throw execption when trying to find not existent category`() {

        every { categoryRepository.findById(any()) } returns Optional.empty()

        assertThrows<RuntimeException> { categoryService.getCategoryById(1) }
        verify(exactly = 1) { categoryRepository.findById(1) }
    }
}