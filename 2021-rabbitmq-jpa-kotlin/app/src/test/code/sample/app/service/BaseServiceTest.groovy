package sample.app.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import sample.app.MainApplication

@SpringBootTest(classes = MainApplication.class)
abstract class BaseServiceTest<SERVICE> {

    @Autowired
    protected SERVICE service

}
