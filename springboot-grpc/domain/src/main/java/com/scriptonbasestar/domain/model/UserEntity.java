package com.scriptonbasestar.domain.model;

import com.scriptonbasestar.domain.type.UserGradeType;
import lombok.*;

import javax.persistence.*;

/**
 * @author chaeeung.e
 * @since 2017-06-27
 */
@Entity(name = "UserEntity")
@Table(name = "T_USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString
public class UserEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 100)
	private String name;

	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private UserGradeType userGradeType;

}
