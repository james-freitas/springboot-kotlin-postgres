package com.codeonblue.sample.resource

import com.codeonblue.sample.SampleApplication
import com.codeonblue.sample.domain.Category
import com.codeonblue.sample.repository.CategoryRepository
import io.zonky.test.db.AutoConfigureEmbeddedDatabase
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
import org.springframework.test.web.servlet.get

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

    @BeforeEach
    private fun before() {
        categoryRepository.deleteAll()
    }

    @Test
    fun `Should get 200 status when trying to find an existent category`() {

        val category = Category(
            id = 1,
            description = "Category test"
        )

        categoryRepository.save(category)

        mockMvc.get("$CATEGORIES_PATH/1") {
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

    companion object {
        private const val CATEGORIES_PATH = "/api/v1/categories"
    }

}