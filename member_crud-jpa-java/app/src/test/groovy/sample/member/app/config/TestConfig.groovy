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
		private UserRepository userRepository

		@PostConstruct
		void run() {
			(1..10).each {
				def userEntity = new UserEntity(
						username: "username$it",
						realname: "realname$it",
						email: "email$it@email.com"
				)
				userEntity.nickname = "nickname$it"
				userEntity.password = "passw0rd$it"
				userRepository.save(userEntity)
			}
		}
	}

	@Bean
	LoadData loadData() {
		return new LoadData()
	}

}
