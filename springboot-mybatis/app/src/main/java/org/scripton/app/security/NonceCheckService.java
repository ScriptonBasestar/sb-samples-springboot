package org.scripton.app.security;

/**
 * @author archmagece@gmail.com
 * @since 2018-02-08 오후 6:07
 */
public interface NonceCheckService {
    boolean isDuplicate(String nonce);
}
