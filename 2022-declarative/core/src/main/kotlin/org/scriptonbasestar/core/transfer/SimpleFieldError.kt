package org.scriptonbasestar.core.transfer

data class SimpleFieldError(
    val path: String,
    val message: String,
    val code: String
)
