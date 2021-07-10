package org.scripton.domain.init


import org.scripton.domain.config.DomainConfig

import org.junit.Test
import org.junit.runner.RunWith
import org.scripton.domain.DomainTestApplication
import org.scripton.domain.mapper.TemplateMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author archmagece
 * @since 2017-08-23
 */
@RunWith(SpringRunner)
@SpringBootTest(classes = [DomainTestApplication.class])
@SpringBootConfiguration
@Import(DomainConfig.class)
class EmptyInitTest {

	@Autowired
	TemplateMapper sendHistoryMapper

	@Test
	void 'test'(){
	}

}
