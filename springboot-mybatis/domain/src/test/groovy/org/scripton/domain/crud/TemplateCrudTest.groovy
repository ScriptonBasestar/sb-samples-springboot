package org.scripton.domain.crud

import org.scripton.core.type.TemplateType
import org.scripton.domain.config.DomainConfig
import org.scripton.domain.DomainTestApplication
import org.scripton.domain.mapper.TemplateMapper
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.scripton.domain.model.TemplateEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

/**
 * @author archmagece
 * @since 2017-08-23
 */
@RunWith(SpringRunner)
@SpringBootTest(classes = [DomainTestApplication.class])
@SpringBootConfiguration
@Import(DomainConfig.class)
class TemplateCrudTest {

	@Autowired
	TemplateMapper templateRepository

	List<Long> templateIds = new ArrayList<>()
	static final String MACHINE_NAME="FS"

	@Before
	@Transactional
	void 'C template insert'(){
		TemplateEntity templateEntity = new TemplateEntity()
		templateEntity.template = """스팸문자 \${amount1}원으로 \${amount2}원 벌기"""
		templateEntity.name = "연습 템플릿1"
		templateEntity.templateType = TemplateType.FREE_MARKER
		templateEntity.callMachine = MACHINE_NAME
		templateEntity.callUser = 2
		templateRepository.create(templateEntity)
		templateIds.add(templateEntity.id)
	}

	@Test
	@Transactional
	void 'RU template read and update'() {
		String originTemplate
		String newTemplate
		for(Long id : templateIds){
			TemplateEntity entity = templateRepository.findOneById(MACHINE_NAME, id)
			originTemplate = entity.template
			newTemplate = """\$\$돈벌기가장쉬운사이트"""
			templateRepository.modifyById(MACHINE_NAME, id, entity.templateType, newTemplate)
			entity = templateRepository.findOneById(MACHINE_NAME, id)
			print("origin $originTemplate changed ${entity.template}")
			Assert.assertTrue(originTemplate != entity.template)
		}
	}

	@After
	void 'D template delete'() {
		templateIds.forEach({ item -> templateRepository.removeById(MACHINE_NAME, item)})
		templateIds.forEach({ item -> Assert.assertNull(templateRepository.removeById(MACHINE_NAME, item))})
	}

}
