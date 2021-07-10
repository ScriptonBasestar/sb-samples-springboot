package sample.member.app.web.signup

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sample.member.core.transfer.EmptyResponseWrapper

import javax.validation.Valid

@RestController
@RequestMapping("/signup/rest")
class SignupRestController {

	@Autowired
	private lateinit var signupService: SignupService

	@RequestMapping(value = ["/checkUsername"], method = [RequestMethod.GET])
	@ResponseBody
	fun checkUsername(@RequestParam username: String): ResponseEntity<*> =
			if (!signupService.isExistsUsername(username)) {
				ResponseEntity.ok().build<Any>()
			} else {
				ResponseEntity.notFound().build<Any>()
			}

	@RequestMapping(value = ["/checkEmail"], method = [RequestMethod.GET])
	@ResponseBody
	fun checkEmail(@RequestParam email: String): ResponseEntity<*> =
			if (!signupService.isExistsEmail(email)) {
				ResponseEntity.ok().build<Any>()
			} else {
				ResponseEntity.notFound().build<Any>()
			}

	@RequestMapping(value = [""], method = [RequestMethod.POST])
	@ResponseBody
	fun signupAction(@RequestBody @Valid requestDto: SignupRequestDto): EmptyResponseWrapper {
		signupService.signup(requestDto)
		return EmptyResponseWrapper.create().success(true)
	}

}
