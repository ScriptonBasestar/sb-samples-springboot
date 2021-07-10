package org.scripton.app.rest.template;

import org.scripton.app.rest.template.dto.request.TemplateRequestDto;
import org.scripton.core.reqres.SBLongIdDto;
import org.scripton.core.reqres.SBPage;
import org.scripton.core.reqres.SBPageResponseWrapper;
import org.scripton.core.reqres.SBPageable;
import org.scripton.domain.mapper.TemplateMapper;
import org.scripton.domain.model.TemplateEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author archmagece
 * @since 2017-08-23
 *
 * callUser 작성자 기록을 하긴하지만 추가적인 권한체크를 하지는 않음
 */
@Service
@Slf4j
public class TemplateService {

	@Autowired
	private TemplateMapper templateMapper;

	public SBPageResponseWrapper<TemplateEntity> list(SBPageable pageable){
		SBPageResponseWrapper<TemplateEntity> wrapper = new SBPageResponseWrapper<>();
		int offset = pageable.getPageNo() * pageable.getPageSize();
		int limit = offset + pageable.getPageSize();

		wrapper.setPage(new SBPage(pageable.getPageNo(), pageable.getPageSize(), templateMapper.count()));
		wrapper.setData(templateMapper.findAll(offset, limit));
		return wrapper;
	}

	@Transactional
	public SBLongIdDto create(TemplateRequestDto requestDto){
		TemplateEntity entity = requestDto.toEntity();
		templateMapper.create(entity);
		return new SBLongIdDto(entity.getId());
	}

	public TemplateEntity readById(Long id){
		return templateMapper.findOneById(id);
	}

}
