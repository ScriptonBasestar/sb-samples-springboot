package org.scriptonbasestar.domain.member.graph

import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface MemberNodeRepository : Neo4jRepository<MemberNode, Long> {
    fun findOneByUuid(uuid: UUID): Optional<MemberNode>
    fun findAllByNameLike(name: String): List<MemberNode>
}
