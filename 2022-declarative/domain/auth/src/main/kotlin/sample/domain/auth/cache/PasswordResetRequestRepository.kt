package sample.domain.auth.cache

import org.springframework.data.repository.CrudRepository
import sample.domain.auth.cache.PasswordResetRequestCache
import java.util.Optional

interface PasswordResetRequestRepository : CrudRepository<PasswordResetRequestCache, String> {
    fun findOneByCode(code: String): Optional<PasswordResetRequestCache>
}
