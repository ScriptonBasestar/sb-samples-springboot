package org.scripton.app.business

import org.junit.runner.RunWith
import org.scripton.app.AppTestApplication
import org.scripton.app.rest.send.SendService
import org.scripton.app.security.NonceCheckService
import org.scripton.domain.config.DomainConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author archmagece@gmail.com
 * @since 2018-03-14 오후 2:11
 */
@RunWith(SpringRunner)
@SpringBootTest(classes = [AppTestApplication])
@SpringBootConfiguration
@Import(DomainConfig.class)
class SendMessageTest {
	@Autowired
	NonceCheckService nonceCheckService
}
