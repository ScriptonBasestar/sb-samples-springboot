package org.scriptonbasestar.domain.board.graph

import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ArticleNodeRepository : Neo4jRepository<ArticleNode, UUID>

