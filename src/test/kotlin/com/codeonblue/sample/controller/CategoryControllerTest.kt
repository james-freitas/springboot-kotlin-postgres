package com.codeonblue.sample.controller

import com.codeonblue.sample.dto.CategoryDto
import com.codeonblue.sample.service.CategoryService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@WebMvcTest(CategoryController::class)
@AutoConfigureMockMvc
internal class CategoryControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var categoryService: CategoryService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `Should get a category for a given id`() {

        val categoryDto = CategoryDto(
                        id = 1,
                        description = "Category test"
                    )

        given(categoryService.findById(1))
            .willReturn(categoryDto)

        mockMvc.get("$CATEGORIES_PATH/1") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.description") { value("Category test") }
        }
    }

    @Test
    fun `Should get a category list successfully`() {

        val categoryList = listOf(
            CategoryDto(
                id = 1,
                description = "Category 1"
            ),
            CategoryDto(
                id = 2,
                description = "Category 2"
            )
        )

        given(categoryService.findAll())
            .willReturn(categoryList)

        mockMvc.get(CATEGORIES_PATH) {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }

            jsonPath("$") { isNotEmpty }
            jsonPath("$[0].description") { value(categoryList[0].description) }
            jsonPath("$[1].description") { value(categoryList[1].description) }
        }
    }

    @Test
    fun `Should create a category successfully`() {

        val category = CategoryDto(
            description = "New category"
        )

        given(categoryService.create(category))
            .willReturn(CategoryDto(
                id = 1,
                description = "New category"
            ))

        val categoryToJson = objectMapper.writeValueAsString(category)

        mockMvc.post(CATEGORIES_PATH) {
            contentType = MediaType.APPLICATION_JSON
            content = categoryToJson
        }.andExpect {
            status { isCreated }
        }
    }

    companion object {
        private const val CATEGORIES_PATH = "/api/v1/categories"
    }
}
