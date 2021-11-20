package sample.core.transfer

class EmptyResponseWrapper {
    private var success = true
    fun success(success: Boolean): EmptyResponseWrapper {
        this.success = success
        return this
    }

    private var lang: String? = null
    fun lang(lang: String?): EmptyResponseWrapper {
        this.lang = lang
        return this
    }

    private var code: String? = null
    fun code(code: String?): EmptyResponseWrapper {
        this.code = code
        return this
    }

    private var message: String? = null
    fun message(message: String?): EmptyResponseWrapper {
        this.message = message
        return this
    }

    companion object {
        fun create(): EmptyResponseWrapper {
            return EmptyResponseWrapper()
        }
    }
}
