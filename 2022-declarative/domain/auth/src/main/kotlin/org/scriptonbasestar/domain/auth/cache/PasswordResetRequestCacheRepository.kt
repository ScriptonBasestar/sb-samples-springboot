package org.scriptonbasestar.domain.auth.cache

import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface PasswordResetRequestCacheRepository : CrudRepository<PasswordResetRequestCache, String> {
    fun findOneByCode(code: String): Optional<PasswordResetRequestCache>
}
