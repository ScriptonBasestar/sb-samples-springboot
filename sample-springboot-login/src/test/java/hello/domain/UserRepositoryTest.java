package hello.domain;

import hello.domain.model.UserEntity;
import hello.domain.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

/**
 * @author archmagece
 * @date 2017-05-13
 * <p>
 * CRUD
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void insertAndSelect() {
		UserEntity userEntity = new UserEntity();
		userEntity.setPassword("jiowfwjio");
		userEntity.setUsername("ejwioe@fjwieowe.com");
		userEntity = userRepository.saveAndFlush(userEntity);

		UserEntity userEntity1 = userRepository.findByUsername("ejwioe@fjwieowe.com");
		Assert.assertEquals(userEntity, userEntity1);
	}

	@Test(expected = ConstraintViolationException.class)
	public void usernameValidationCheck_length() {
		//less than 5
		UserEntity userEntity = new UserEntity();
		userEntity.setPassword("jiowfwjio");
		userEntity.setUsername("jef");
		userRepository.saveAndFlush(userEntity);
	}

	@Test(expected = ConstraintViolationException.class)
	public void usernameValidationCheck() {
		//email pattern
		UserEntity userEntity = new UserEntity();
		userEntity.setPassword("jiowfwjio");
		userEntity.setUsername("wfjeioojwewjfeiowoe");
		userRepository.saveAndFlush(userEntity);
	}

}
