package sample.domain.jpa.relation

import org.springframework.data.neo4j.core.schema.Node
import java.util.UUID

@Node
class UserNode (
    val id: Long,
    val uuid: UUID,
    val name: String,
)
