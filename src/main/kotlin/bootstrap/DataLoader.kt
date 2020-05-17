package bootstrap

import com.codeonblue.sample.repository.CategoryRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration

@Configuration
class DataLoader(val categoryRepository: CategoryRepository): CommandLineRunner{

    private val logger: Logger = LoggerFactory.getLogger(DataLoader::class.java)

    override fun run(vararg args: String?) {
        logger.info(">>>>>>>>>> Dataloader: ${categoryRepository.count()}")
    }
}