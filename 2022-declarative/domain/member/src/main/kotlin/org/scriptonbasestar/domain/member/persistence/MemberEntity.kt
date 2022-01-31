package org.scriptonbasestar.domain.member.persistence

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.Type
import org.scriptonbasestar.base.BaseSeqEntity
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(
    name = "T_USER",
    indexes = [
        Index(name = "IDX__T_USER__realname", columnList = "realname", unique = false),
    ]
)
@DynamicUpdate
class MemberEntity(
    @field:Size(min = 2, max = 20)
    @Column(length = 30, unique = true, nullable = false)
    val username: String,

    @field:Size(min = 2, max = 20)
    @Column(length = 30, nullable = false)
    val realname: String,

    @field:Size(min = 5, max = 100)
    @Column(length = 100, unique = true, nullable = false)
    val email: String?,

    @field:Type(type = "sample.domain.jpa.usertype.BCryptUserType")
    @field:JsonIgnore
    @Column(length = 60, nullable = false)
    val password: String
) : BaseSeqEntity() {

    @field:Size(min = 5, max = 100)
    @field:NotBlank
    @Column(length = 20)
    var nickname: String? = null

    override fun toString(): String {
        return super.buildStringHelper()
            .add("username", username)
            .add("realname", realname)
            .add("email", email)
            .add("nickname", nickname)
            .toString()
    }
}
