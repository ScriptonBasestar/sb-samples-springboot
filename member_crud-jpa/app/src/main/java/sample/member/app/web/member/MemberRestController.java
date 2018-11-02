package sample.member.app.web.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import sample.member.core.transfer.PageResponseWrapper;
import sample.member.core.transfer.SingleResponseWrapper;
import sample.member.domain.model.UserEntity;

@RestController
@RequestMapping("/member/rest")
public class MemberRestController {

	@Autowired
	private MemberService friendService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public PageResponseWrapper<UserEntity> list(@PageableDefault Pageable pageable) {
		Page<UserEntity> page = friendService.list(pageable);
		return PageResponseWrapper.<UserEntity>create()
				.totalElements(page.getTotalElements())
				.totalPages(page.getTotalPages())
				.data(page.getContent())
				.success(true);
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public SingleResponseWrapper<UserEntity> detail(@RequestParam String username) {
		return SingleResponseWrapper.<UserEntity>create()
				.data(friendService.detail(username))
				.success(true);
	}

}
