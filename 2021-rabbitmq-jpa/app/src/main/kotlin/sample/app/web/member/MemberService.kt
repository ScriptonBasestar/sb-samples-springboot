package sample.app.web.member

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import sample.core.exception.DataNotFoundException
import sample.core.util.loggerUtil
import sample.domain.jpa.model.UserEntity
import sample.domain.jpa.repository.UserRepository

@Service
class MemberService(
    @Autowired private val userRepository: UserRepository
) {
    val log = loggerUtil()

    fun list(pageable: Pageable): Page<UserEntity> {
        log.trace("회원 목록 가져오기 - page: {} size: {}", pageable.pageNumber, pageable.pageSize)
        return userRepository.findAll(pageable)
    }

    fun detail(username: String): UserEntity {
        log.trace("회원 상세 가져오기 - username: {}", username)
        return userRepository.findByUsername(username).orElseThrow { DataNotFoundException("요청하신 데이터가 없습니다.") }
    }
}
