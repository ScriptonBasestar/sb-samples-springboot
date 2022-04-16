package org.scriptonbasestar.domain.member.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MemberEntityRepository : JpaRepository<MemberEntity, Long> {
    fun findByUsername(username: String): Optional<MemberEntity>
    fun findByEmail(email: String): Optional<MemberEntity>
}
