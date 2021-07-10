package org.scripton.domain.model;

import org.scripton.core.type.TemplateType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author archmagece
 * @since 2018-09-19
 */
@Data
public class TemplateEntity {

	private Long id;

	@Size(max = 50)
	private String name;
	private TemplateType templateType;
	@Size(max = 4000)
	private String template;
	private Date createdAt;

}
