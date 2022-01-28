package sample.domain.jpa.relation

import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface UserNodeRepository : Neo4jRepository<UserNode, Long> {
    fun findOneByUuid(uuid: UUID): Optional<UserNode>
    fun findAllByNameLike(name: String): List<UserNode>
}
