package org.scriptonbasestar.app.auth.dto.oauth.token

data class PasswordGrantRequest(
    val clientId: String,
    val clientSecret: String,
    val username: String,
    val password: String,
    val scope: String,
)
