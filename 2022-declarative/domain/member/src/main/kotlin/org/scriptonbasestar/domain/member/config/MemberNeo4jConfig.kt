package org.scriptonbasestar.domain.member.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.neo4j.config.EnableNeo4jAuditing
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories

@Configuration
@EnableNeo4jAuditing
@EnableNeo4jRepositories(basePackages = ["org.scriptonbasestar.domain.member"])
class MemberNeo4jConfig {
}
