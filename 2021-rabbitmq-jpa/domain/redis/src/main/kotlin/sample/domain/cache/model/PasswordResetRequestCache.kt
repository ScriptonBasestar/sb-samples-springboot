package sample.domain.cache.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed

class PasswordResetRequestCache(
    @Indexed
    val code: String,

    @TimeToLive
    val expire: Int = 60 * 60 * 10,
) {
    @Id
    var id: String? = null
}
