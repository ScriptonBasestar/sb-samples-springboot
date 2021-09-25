package sample.app.web.signup

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.AssertTrue
import javax.validation.constraints.Email

data class SignupRequestDto(

    @field:Length(min = 2, max = 10)
//    @field:NotNull
    val username: String,

    @field:Length(min = 2, max = 10)
//    @field:NotNull
    val realname: String,

    @field:Length(min = 5, max = 50)
//    @field:NotNull
    @field:Email
    val email: String,

    @field:Length(min = 1, max = 20)
    val nickname: String?,

    @field:Length(min = 5, max = 20)
//    @field:NotNull
    val password: String,

    @field:Length(min = 5, max = 20)
//    @field:NotNull
    val passwordConfirm: String
) {
    @AssertTrue
    fun isPasswordEqual() = password == passwordConfirm
}
