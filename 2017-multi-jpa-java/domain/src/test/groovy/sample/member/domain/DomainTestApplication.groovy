package sample.member.domain


import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration

@SpringBootApplication
class DomainTestApplication {
	static void main(String[] args) {
		SpringApplication.run(DomainTestApplication.class, args)
	}
}
