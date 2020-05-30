package com.codeonblue.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SampleApplication

@SuppressWarnings("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<SampleApplication>(*args)
}
