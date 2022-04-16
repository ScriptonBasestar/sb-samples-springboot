package org.scriptonbasestar.domain.board.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ArticleRepository :
    JpaRepository<ArticleEntity, Long>,
    JpaSpecificationExecutor<ArticleEntity> {
    fun findOneByUuid(uuid: UUID): Optional<ArticleEntity>
}
