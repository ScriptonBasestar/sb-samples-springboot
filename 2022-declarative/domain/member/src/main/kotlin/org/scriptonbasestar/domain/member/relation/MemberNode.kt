package org.scriptonbasestar.domain.member.relation

import org.springframework.data.neo4j.core.schema.Node
import java.util.UUID

@Node
class MemberNode(
    val id: Long,
    val uuid: UUID,
    val name: String,
)
