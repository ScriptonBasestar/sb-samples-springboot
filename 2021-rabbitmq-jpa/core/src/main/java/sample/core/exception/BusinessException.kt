package sample.core.exception

class BusinessException : BaseException {
    constructor(message: String) : super(message)
    constructor(message: String, e: Throwable) : super(message, e)
}
