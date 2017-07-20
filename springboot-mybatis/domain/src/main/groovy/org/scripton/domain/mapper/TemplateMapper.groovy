package org.scripton.domain.mapper

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.scripton.domain.model.TemplateEntity

/**
 * @author archmagece
 * @since 2018-09-19
 */
@Mapper
interface TemplateMapper {

	@Select("SELECT count(*) FROM t_template")
	int count()

	@Select("SELECT * FROM t_template limit #{limit} offset #{offset}")
	List<TemplateEntity> findAll(@Param("offset") int offset, @Param("limit") int limit)

	void create(TemplateEntity templateEntity)

	@Select("SELECT * FROM t_template WHERE id = #{id}")
	TemplateEntity findOneById(@Param("id") long id)
}
