package org.scriptonbasestar.app.auth.dto.oauth.token

import java.time.Instant

data class AccessToken(
    val issuedAt: Instant,
    val expiresAt: Instant,
)
