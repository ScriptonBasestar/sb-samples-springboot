package sample.domain.cache.repository

import org.springframework.data.repository.CrudRepository
import sample.domain.cache.model.PasswordResetRequestCache
import java.util.*

interface PasswordResetRequestRepository : CrudRepository<PasswordResetRequestCache, String> {
    fun findOneByCode(code: String): Optional<PasswordResetRequestCache>
}
