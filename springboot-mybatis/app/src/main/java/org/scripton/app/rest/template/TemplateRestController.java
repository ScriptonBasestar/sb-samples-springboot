package org.scripton.app.rest.template;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.scripton.app.rest.template.dto.request.TemplateRequestDto;
import org.scripton.domain.model.TemplateEntity;
import lombok.extern.slf4j.Slf4j;
import org.scripton.core.reqres.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author archmagece
 * @since 2017-08-23
 */
@Controller
@RequestMapping("/rest/template")
@Slf4j
public class TemplateRestController {

	@Autowired
	private TemplateService templateService;

	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "x-auth-nonce", value = "클라이언트 머신에서 호출시 고유호출값 serial no or string", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "Authorization", value = "Authorization : SB_SERVER_AUTH_CODE XXXXXXX\nserver auth code", required = true, dataType = "string", paramType = "header"),

			@ApiImplicitParam(name = "pageNo",   value = "페이지 번호", defaultValue = "0",  dataType = "integer", paramType = "query"),
			@ApiImplicitParam(name = "pageSize", value = "페이지 크기", defaultValue = "10", dataType = "integer", paramType = "query"),
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public SBPageResponseWrapper<TemplateEntity> list(SBPageable pageable){
		long now = System.currentTimeMillis();
		SBPageResponseWrapper<TemplateEntity> wrapper = templateService.list(pageable);
		return wrapper.leadTime(System.currentTimeMillis() - now);
	}

	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "x-auth-nonce", value = "클라이언트 머신에서 호출시 고유호출값 serial no or string", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "Authorization", value = "Authorization : SB_SERVER_AUTH_CODE XXXXXXX\nserver auth code", required = true, dataType = "string", paramType = "header"),
	})
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public SBOneResponseWrapper<SBLongIdDto> create(@RequestBody TemplateRequestDto requestDto){
		long now = System.currentTimeMillis();
		SBOneResponseWrapper<SBLongIdDto> wrapper = new SBOneResponseWrapper();
		wrapper.setData(templateService.create(requestDto));
		return wrapper.leadTime(System.currentTimeMillis() - now);
	}

	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "x-auth-nonce", value = "클라이언트 머신에서 호출시 고유호출값 serial no or string", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "Authorization", value = "Authorization : SB_SERVER_AUTH_CODE XXXXXXX\nserver auth code", required = true, dataType = "string", paramType = "header"),
	})
	@RequestMapping(value = "/byId/{id}", method = RequestMethod.GET)
	@ResponseBody
	public SBOneResponseWrapper<TemplateEntity> readById(@PathVariable Long id){
		long now = System.currentTimeMillis();
		TemplateEntity templateEntity = templateService.readById(id);
		SBOneResponseWrapper<TemplateEntity> wrapper = new SBOneResponseWrapper<>();
		wrapper.setData(templateEntity);
		return wrapper.leadTime(System.currentTimeMillis() - now);
	}

}
