package hello.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author archmagece
 * @date 2017-05-12
 */
@Entity
@Table(
		name = "T_USER",
		indexes = @Index(unique = true, name = "idx_t_user_username", columnList = "username")
)
@Data
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue
	private Long id;

	@Size(min = 5, max = 100)
	@NotNull
	@Email
	private String username;

	@NotNull
	@JsonIgnore
	@Size(min = 5)
	@Column(nullable = false, length = 1024)
	private String password;

	@Transient
	private String passwordConfirm;

	@AssertTrue
	public boolean passwordSame() {
		return password.equals(passwordConfirm);
	}

}
