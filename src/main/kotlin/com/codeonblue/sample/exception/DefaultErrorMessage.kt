package com.codeonblue.sample.exception

import java.time.OffsetDateTime
import org.springframework.http.HttpStatus

data class DefaultErrorMessage(
    val timestamp: OffsetDateTime? = OffsetDateTime.now(),
    val status: Int? = HttpStatus.INTERNAL_SERVER_ERROR.value(),
    val error: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    val message: String? = "",
    val path: String
)
