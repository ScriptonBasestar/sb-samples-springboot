package sample.app.web.signup

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sample.core.exception.BusinessException
import sample.domain.model.UserEntity
import sample.domain.repository.UserRepository

@Slf4j
@Service
class SignupService {

    companion object {
        val log = LoggerFactory.getLogger(SignupService::class.java)!!
    }

    @Autowired
    private lateinit var userRepository: UserRepository

    fun isExistsUsername(username: String): Boolean {
        log.trace("아이디(username) 중복 확인, username : {}", username)
        return userRepository.findByUsername(username).isPresent
    }

    fun isExistsEmail(email: String): Boolean {
        log.trace("이메일 중복 확인 - email : {}", email)
        return userRepository.findByEmail(email).isPresent
    }

    @Transactional
    fun signup(requestDto: SignupRequestDto) {
        log.trace(
            "회원가입 액션 - username:{}, realname:{}, email:{}, nickname:{}, passwordSame:{}",
            requestDto.username,
            requestDto.realname,
            requestDto.email,
            requestDto.nickname,
            requestDto.isPasswordEqual()
        )

        val userEntity =
            UserEntity(requestDto.username, requestDto.realname, requestDto.email, requestDto.password).apply {
                nickname = requestDto.nickname
            }
        try {
            userRepository.save(userEntity)
        } catch (e: DataIntegrityViolationException) {
            throw BusinessException("계정정보가 중복됩니다. 가입된 계정이있는지 확인 해 주세요.")
        }
    }
}
