package sample.app

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile

@SpringBootTest
@Profile("test")
class ApplicationTests {

    @Test
    fun contextLoads() {
        println("Main application base test")
    }
}
