package org.scriptonbasestar.domain.board.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.neo4j.config.EnableNeo4jAuditing
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories
import java.util.*

@Configuration
@EnableNeo4jRepositories
@EnableNeo4jAuditing
class BoardNeo4jConfig
