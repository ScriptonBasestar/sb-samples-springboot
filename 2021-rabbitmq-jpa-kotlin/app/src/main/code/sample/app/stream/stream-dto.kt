package sample.app.stream

import java.util.*

data class MessageRegisterDto(
    val id: Long,
    val email: String,
    val createdAt: Date,
)

data class MessageEmailauthDto(
    val id: Long,
    val email: String,
    val success: Boolean,
    val createdAt: Date
)
