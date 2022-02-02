package org.scriptonbasestar.domain.board.graph

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import org.springframework.data.neo4j.core.support.UUIDStringGenerator
import java.util.*

@Node("User")
class UserNode {
    @Id
    @GeneratedValue(UUIDStringGenerator::class)
    lateinit var id: UUID

    @Relationship(type = "WRITE", direction = Relationship.Direction.OUTGOING)
    lateinit var articles: List<ArticleNode>

    @Relationship(type = "WRITE", direction = Relationship.Direction.OUTGOING)
    lateinit var comments: List<CommentNode>
}
