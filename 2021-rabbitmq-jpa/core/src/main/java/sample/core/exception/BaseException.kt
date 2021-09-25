package sample.core.exception

open class BaseException : RuntimeException {
    constructor(message: String) : super(message)
    constructor(message: String, e: Throwable) : super(message, e)
}
