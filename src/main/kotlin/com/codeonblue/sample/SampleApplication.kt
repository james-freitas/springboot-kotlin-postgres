package com.codeonblue.sample

import com.codeonblue.sample.domain.Category
import com.codeonblue.sample.repository.CategoryRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SampleApplication(val categoryRepository: CategoryRepository) : CommandLineRunner {

	private val logger: Logger = LoggerFactory.getLogger(SampleApplication::class.java)

	override fun run(vararg args: String?) {
		if (categoryRepository.count() == 0L) {
			logger.info(">>>> Number of records in DB before insert: ${categoryRepository.count()}")
			loadData();
		}
		logger.info(">>>>> Final number of records in the DB: ${categoryRepository.count()}")
	}

	private fun loadData() {

		val category = Category(
			description = "Eletronics"
		)
		categoryRepository.save(category)
	}
}

fun main(args: Array<String>) {
	runApplication<SampleApplication>(*args)
}
