package sample.member.app.web.member

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import sample.member.core.exception.DataNotFoundException
import sample.member.domain.model.UserEntity
import sample.member.domain.repository.UserRepository

@Slf4j
@Service
class MemberService {

	companion object {
		val log = LoggerFactory.getLogger(MemberService::class.java)!!
	}

	@Autowired
	private val userRepository: UserRepository? = null

	fun list(pageable: Pageable): Page<UserEntity> {
		log.trace("회원 목록 가져오기 - page: {} size: {}", pageable.pageNumber, pageable.pageSize)
		return userRepository!!.findAll(pageable)
	}

	fun detail(username: String): UserEntity {
		log.trace("회원 상세 가져오기 - username: {}", username)
		return userRepository!!.findByUsername(username).orElseThrow { DataNotFoundException("요청하신 데이터가 없습니다.") }
	}
}
