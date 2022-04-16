// package sample.app.web.signup
//
// import javax.validation.constraints.AssertTrue
// import javax.validation.constraints.Email
// import javax.validation.constraints.NotBlank
// import javax.validation.constraints.Size
//
// data class SignupRequestDto(
//    @field:Size(min = 2, max = 10)
//    @field:NotBlank
//    val username: String,
//
//    @field:Size(min = 2, max = 10)
//    @field:NotBlank
//    val realname: String,
//
//    @field:Size(min = 5, max = 50)
//    @field:NotBlank
//    @field:Email
//    val email: String,
//
//    @field:Size(min = 1, max = 20)
//    val nickname: String?,
//
//    @field:Size(min = 5, max = 20)
//    @field:NotBlank
//    val password: String,
//
//    @field:Size(min = 5, max = 20)
//    @field:NotBlank
//    val passwordConfirm: String
// ) {
//    @AssertTrue
//    fun isPasswordEqual() = password == passwordConfirm
// }
