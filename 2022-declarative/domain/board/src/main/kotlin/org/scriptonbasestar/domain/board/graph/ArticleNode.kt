package org.scriptonbasestar.domain.board.graph

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import org.springframework.data.neo4j.core.support.UUIDStringGenerator
import java.util.*

@Node
class ArticleNode {
    @Id
    @GeneratedValue(UUIDStringGenerator::class)
    lateinit var id: UUID

    val jj by lazyOf(Int)

    @Relationship(type = "AUTHORED_BY", direction = Relationship.Direction.INCOMING)
    lateinit var author: List<CommentNode>

    @Relationship(type = "CONTAINS", direction = Relationship.Direction.OUTGOING)
    lateinit var comments: List<CommentNode>
}
