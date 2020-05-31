package com.codeonblue.sample.exception

import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class GlobalControllerExceptionHandler {

    @ResponseStatus(NOT_FOUND) // 404
    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleNotFound(ex: Throwable, request: WebRequest?) = DefaultErrorMessage(
        status = NOT_FOUND.value(),
        error = NOT_FOUND,
        message = "Resource was not found.",
        path = (request as ServletWebRequest).request.requestURI
    ).let { ResponseEntity(it, NOT_FOUND) }
}
