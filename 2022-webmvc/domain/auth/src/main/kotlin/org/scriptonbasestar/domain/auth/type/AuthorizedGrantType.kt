package org.scriptonbasestar.domain.auth.type

enum class AuthorizedGrantType(val value: String) {
    IMPLICIT("implicit"),
    REFRESH_TOKEN("refresh_token"),
    PASSWORD("password"),
    AUTHORIZATION_CODE("authorization_code"),
    CLIENT_CREDENTIALS("client_credentials"),
}
