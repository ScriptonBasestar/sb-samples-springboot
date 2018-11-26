package sample.member.app.service

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import sample.member.app.MainApplication

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MainApplication.class)
abstract class BaseServiceTest<SERVICE> {

	@Autowired
	protected SERVICE service

}
