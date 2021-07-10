package sample.member.app.service

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import sample.member.app.MainApplication

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
abstract class BaseServiceTest<SERVICE> {

	@Autowired
	protected SERVICE service

}
