package sample.domain.jpa.model.user

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.Type
import sample.domain.jpa.model.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "T_USER", indexes = [Index(name = "IDX__T_USER__realname", columnList = "realname", unique = false)])
@DynamicUpdate
class UserEntity(
    @field:Column(length = 20, unique = true, nullable = false)
    val username: String,
    @field:Column(length = 20, nullable = false)
    val realname: String,
    @field:Column(length = 50, unique = true, nullable = false)
    val email: String?, // 56
    @field:Type(type = "sample.domain.jpa.usertype.BCryptUserType")
    @field:Column(length = 60, nullable = false)
    @field:JsonIgnore
    val password: String
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

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
