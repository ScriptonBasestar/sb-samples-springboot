package sample.core.transfer

class SingleResponseWrapper<RESPONSE> {
    private var success = true
    fun success(success: Boolean): SingleResponseWrapper<RESPONSE> {
        this.success = success
        return this
    }

    private var lang: String? = null
    fun lang(lang: String?): SingleResponseWrapper<RESPONSE> {
        this.lang = lang
        return this
    }

    private var code: String? = null
    fun code(code: String?): SingleResponseWrapper<RESPONSE> {
        this.code = code
        return this
    }

    private var message: String? = null
    fun message(message: String?): SingleResponseWrapper<RESPONSE> {
        this.message = message
        return this
    }

    private var data: RESPONSE? = null
    fun data(data: RESPONSE): SingleResponseWrapper<RESPONSE> {
        this.data = data
        return this
    }

    companion object {
        fun <RESPONSE0> create(): SingleResponseWrapper<RESPONSE0> {
            return SingleResponseWrapper()
        }
    }
}
