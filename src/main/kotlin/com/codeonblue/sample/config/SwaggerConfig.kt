package com.codeonblue.sample.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.SwaggerResource
import springfox.documentation.swagger.web.SwaggerResourcesProvider
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Primary
@Configuration
@EnableSwagger2
class SwaggerConfig : SwaggerResourcesProvider {

    override fun get(): MutableList<SwaggerResource> {
        val swaggerResource = SwaggerResource()
        swaggerResource.name = "default"
        swaggerResource.location = "/api-docs-swagger.yaml"
        swaggerResource.swaggerVersion = "1.0"
        return mutableListOf(swaggerResource)
    }

    @Bean
    fun apiDocumentation() = Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
}
