package sample.app

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile

@SpringBootTest
@Profile("test")
class MainApplicationTests {

    @Test
    fun contextLoads(){
        println("Main application base test")
    }
}
