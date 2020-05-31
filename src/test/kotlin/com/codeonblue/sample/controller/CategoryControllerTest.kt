package com.codeonblue.sample.controller

import com.codeonblue.sample.dto.CategoryDto
import com.codeonblue.sample.service.CategoryService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(CategoryController::class)
@AutoConfigureMockMvc
internal class CategoryControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var categoryService: CategoryService

    @Test
    fun `Should get a category for a given id`() {

        val categoryDto = CategoryDto(
                        id = 1,
                        description = "Category test"
                    )

        given(categoryService.getCategoryById(1))
            .willReturn(categoryDto)

        mockMvc.get("/api/v1/categories/1") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.description") { value("Category test") }
        }
    }
}
