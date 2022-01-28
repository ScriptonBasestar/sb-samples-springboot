package sample.domain.cache.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

public class PasswordResetRequestCache {
    @Id
    private String id;

    @Indexed
    private String code;

    @TimeToLive
    private Integer expire = 60 * 60 * 10;
}
