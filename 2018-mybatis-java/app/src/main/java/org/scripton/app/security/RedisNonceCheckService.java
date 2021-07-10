package org.scripton.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author archmagece@gmail.com
 * @since 2018-02-08 오후 6:35
 */
public class RedisNonceCheckService implements NonceCheckService{

    @Autowired
    private RedisTemplate<String, Boolean> redisBooleanTemplate;

    @Override
    public boolean isDuplicate(String nonce) {
        if(redisBooleanTemplate.opsForSet().getOperations().hasKey(nonce)){
            redisBooleanTemplate.expire(nonce, 1, TimeUnit.HOURS);
            return true;
        }
        redisBooleanTemplate.opsForSet().add(nonce, true);
        redisBooleanTemplate.expire(nonce, 1, TimeUnit.HOURS);
        return false;
    }
}
