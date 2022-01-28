package sample.domain

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class DomainTestApplication

fun main(args: Array<String>) {
    SpringApplication.run(DomainTestApplication::class.java, *args)
}
