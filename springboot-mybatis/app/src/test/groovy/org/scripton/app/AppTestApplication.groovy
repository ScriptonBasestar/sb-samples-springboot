package org.scripton.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * @author archmagece
 * @since 2017-08-23
 */
@SpringBootApplication
//@ComponentScan
@EnableAutoConfiguration
class AppTestApplication {
	static void main(String[] args) {
		SpringApplication.run(AppTestApplication.class, args)
	}
}
