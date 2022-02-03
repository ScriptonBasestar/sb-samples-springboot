package org.scriptonbasestar.app.auth.dto.oauth.token

data class ClientCredentialRequest(
    val clientId: String,
    val clientSecret: String,
)
