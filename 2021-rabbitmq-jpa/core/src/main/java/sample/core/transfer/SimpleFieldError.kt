package sample.core.transfer

data class SimpleFieldError(
    val path: String,
    val message: String,
    val code: String
)
