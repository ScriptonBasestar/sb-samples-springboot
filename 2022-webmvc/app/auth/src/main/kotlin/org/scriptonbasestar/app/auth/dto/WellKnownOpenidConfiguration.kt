package org.scriptonbasestar.app.auth.dto

data class WellKnownOpenidConfiguration(
    val version: String,
    val issuer: String,
    val authorizationEndpoint: String,
    val tokenEndpoint: String,
    val revocationEndpoint: String,
    val userinfoEndpoint: String,
    val jwksUri: String,
    val pingRevokedSrisEndpoint: String,
    val pingEndSessionEndpoint: String,
    val scopesSupported: List<String>,
    val claimsSupported: List<String>,
    val responseTypesSupported: List<String>,
    val responseModeSupported: List<String>,
    val subjectTypesSupported: List<String>,
    val idTokenSigningAlgValuesSupported: List<String>,
    val tokenEndpointAuthMethodsSupported: List<String>,
    val claimTypesSupported: List<String>,
    val claimsParameterSupported: Boolean,
    val requestParameterSupported: Boolean,
    val requestUriParameterSupported: Boolean,
)
