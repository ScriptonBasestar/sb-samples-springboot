package sample.member.app.web.signup;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class SignupRequestDto {

	@Length(min = 2, max = 10)
	@NotNull
	private String username;

	@Length(min = 2, max = 10)
	@NotNull
	private String realname;

	@Length(min = 5, max = 50)
	@NotNull
	@Email
	private String email;

	@Length(min = 1, max = 20)
	private String nickname;

	@Length(min = 5, max = 20)
	@NotNull
	private String password;

	@Length(min = 5, max = 20)
	@NotNull
	private String passwordConfirm;

	@AssertTrue
	public boolean isPasswordSame() {
		return password.equals(passwordConfirm);
	}
}
