package sample.member.app.web.member

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*
import sample.member.core.transfer.PageResponseWrapper
import sample.member.core.transfer.SingleResponseWrapper
import sample.member.domain.model.UserEntity

@RestController
@RequestMapping("/member/rest")
class MemberRestController {

	@Autowired
	private lateinit var friendService: MemberService

	@RequestMapping(value = ["/list"], method = [RequestMethod.GET])
	@ResponseBody
	fun list(@PageableDefault pageable: Pageable): PageResponseWrapper<UserEntity> =
			friendService.list(pageable).let { page ->
				PageResponseWrapper.create<UserEntity>()
						.totalElements(page.totalElements)
						.totalPages(page.totalPages)
						.data(page.content)
						.success(true)
			}

	@RequestMapping(value = ["/detail"], method = [RequestMethod.GET])
	fun detail(@RequestParam username: String): SingleResponseWrapper<UserEntity> =
			friendService.detail(username).let {userEntity ->
				SingleResponseWrapper.create<UserEntity>()
						.data(userEntity)
						.success(true)
			}

}
