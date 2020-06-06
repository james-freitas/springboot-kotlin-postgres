package com.codeonblue.sample.resource

import com.codeonblue.sample.SampleApplication
import com.codeonblue.sample.domain.Category
import com.codeonblue.sample.dto.CategoryDto
import com.codeonblue.sample.repository.CategoryRepository
import com.fasterxml.jackson.databind.ObjectMapper
import io.zonky.test.db.AutoConfigureEmbeddedDatabase
import org.assertj.core.api.Assertions.assertThat
import org.flywaydb.test.annotation.FlywayTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest(classes = [SampleApplication::class])
@AutoConfigureMockMvc
@ActiveProfiles("test")
@FlywayTest
@AutoConfigureEmbeddedDatabase
@ContextConfiguration
class CategoryResourceTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    private fun before() {
        categoryRepository.deleteAll()
    }

    @Test
    fun `Should get 200 status when trying to find an existent category`() {

        val category = Category(
            description = "Category test"
        )

        val categorySaved = categoryRepository.save(category)

        mockMvc.get("$CATEGORIES_PATH/${categorySaved.id}") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.description") { value("Category test") }
        }
    }

    @Test
    fun `Should get 404 status when trying to find an non existent category`() {

        mockMvc.get("$CATEGORIES_PATH/99") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isNotFound }
        }
    }

    @Test
    fun `Should get 200 status when retrieve a category list`() {

        val categoryList = listOf(
            Category(
                description = "Category 1"
            ),
            Category(
                description = "Category 2"
            )
        )

        categoryRepository.saveAll(categoryList)

        mockMvc.get(CATEGORIES_PATH) {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$") { isArray }
            jsonPath("$") { isNotEmpty }
            jsonPath("$[0].description") { value(categoryList[0].description) }
            jsonPath("$[1].description") { value(categoryList[1].description) }
        }
    }

    @Test
    fun `Should get 201 status when creating a new category`() {

        val category = CategoryDto(
            description = "New category"
        )

        val categoryToJson = objectMapper.writeValueAsString(category)

        mockMvc.post(CATEGORIES_PATH) {
            contentType = MediaType.APPLICATION_JSON
            content = categoryToJson
        }.andExpect {
            status { isCreated }
        }

        assertThat(categoryRepository.count()).isEqualTo(1)
    }

    @Test
    fun `Should get 204 status when trying to delete an existent category`() {

        val category = Category(
            description = "Category test"
        )

        val categorySaved = categoryRepository.save(category)

        assertThat(categoryRepository.count()).isEqualTo(1)

        mockMvc.delete("$CATEGORIES_PATH/${categorySaved.id}") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isNoContent }
        }

        assertThat(categoryRepository.count()).isEqualTo(0)
    }

    @Test
    fun `Should get 404 status when trying to delete a not existent category`() {

        mockMvc.delete("$CATEGORIES_PATH/99") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isNotFound }
        }
    }

    companion object {
        private const val CATEGORIES_PATH = "/api/v1/categories"
    }
}
