package com.codeonblue.sample

import com.codeonblue.sample.domain.Category
import io.zonky.test.db.AutoConfigureEmbeddedDatabase
import org.assertj.core.api.Assertions.assertThat
import org.flywaydb.test.annotation.FlywayTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest(classes = [SampleApplication::class])
@FlywayTest
@AutoConfigureEmbeddedDatabase
@ContextConfiguration
class CategoryRepositoryTest {

    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    @BeforeEach
    fun cleanTable() {
        categoryRepository.deleteAll()
    }

    @Test
    fun `should insert a category successfully`() {

        val category = Category(description = "Category 1")

        categoryRepository.save(category)

        assertThat(categoryRepository.count()).isEqualTo(1)
    }
}
