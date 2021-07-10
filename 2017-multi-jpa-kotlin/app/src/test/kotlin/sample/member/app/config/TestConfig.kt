package sample.member.app.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import sample.member.domain.model.UserEntity
import sample.member.domain.repository.UserRepository
import javax.annotation.PostConstruct


@Configuration
//@Import(DomainConfig.class)
class TestConfig {

	class LoadData {
		@Autowired
		private lateinit var userRepository: UserRepository

		@PostConstruct
		fun run() {
			(1..10).forEach {
				userRepository.save(
						UserEntity(
								"username$it",
								"username$it",
								"username$it@mail.com",
								"username$it"
						).apply {
							nickname = "nickname$it"
						}
				)
			}
		}
	}

	@Bean
	fun loadData(): LoadData = LoadData()

}
