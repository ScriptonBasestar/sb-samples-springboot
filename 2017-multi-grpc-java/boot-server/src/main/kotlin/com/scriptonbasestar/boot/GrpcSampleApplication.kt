package com.scriptonbasestar.boot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class GrpcSampleApplication

fun main(args: Array<String>) {
    SpringApplication.run(GrpcSampleApplication::class.java, *args)
}
