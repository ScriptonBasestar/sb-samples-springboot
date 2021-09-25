package sample.app.web.member

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*
import sample.core.transfer.PageResponseWrapper
import sample.core.transfer.SingleResponseWrapper
import sample.domain.jpa.model.UserEntity

@RestController
@RequestMapping("/member/rest")
class MemberRestController(
    @Autowired private val memberService: MemberService
) {

    @RequestMapping(value = ["/list"], method = [RequestMethod.GET])
    @ResponseBody
    fun list(@PageableDefault pageable: Pageable): PageResponseWrapper<UserEntity> =
        memberService.list(pageable).let { page ->
            PageResponseWrapper.create<UserEntity>()
                .totalElements(page.totalElements)
                .totalPages(page.totalPages)
                .data(page.content)
                .success(true)
        }

    @RequestMapping(value = ["/detail"], method = [RequestMethod.GET])
    fun detail(@RequestParam username: String): SingleResponseWrapper<UserEntity> =
        memberService.detail(username).let { userEntity ->
            SingleResponseWrapper.create<UserEntity>()
                .data(userEntity)
                .success(true)
        }
}
