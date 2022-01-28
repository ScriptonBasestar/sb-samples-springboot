package sample.domain.auth.cache

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed

class PasswordResetRequestCache {
    @Id
    private val id: String? = null

    @Indexed
    private val code: String? = null

    @TimeToLive
    private val expire = 60 * 60 * 10
}
