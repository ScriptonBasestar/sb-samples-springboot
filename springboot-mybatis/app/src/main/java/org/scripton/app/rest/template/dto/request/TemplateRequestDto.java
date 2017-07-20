package org.scripton.app.rest.template.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.scripton.core.type.TemplateType;
import org.scripton.domain.model.TemplateEntity;
import lombok.Data;

/**
 * @author archmagece
 * @since 2017-08-25
 */
@Data
public class TemplateRequestDto {
	private String name;
	@JsonProperty("template_type")
	private TemplateType templateType;
	private String template;

	public TemplateEntity toEntity(){
		TemplateEntity entity = new TemplateEntity();
		entity.setName(this.name);
		entity.setTemplate(this.template);
		entity.setTemplateType(this.templateType);
		return entity;
	}
}

