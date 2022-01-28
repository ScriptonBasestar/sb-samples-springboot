package org.scriptonbasestar.domain.auth.cache

import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface PasswordResetRequestRepository : CrudRepository<PasswordResetRequestCache, String> {
    fun findOneByCode(code: String): Optional<PasswordResetRequestCache>
}
