package sample.core.transfer

class ListResponseWrapper<RESPONSE> {
    private var success = true
    fun success(success: Boolean): ListResponseWrapper<RESPONSE> {
        this.success = success
        return this
    }

    private var lang: String? = null
    fun lang(lang: String?): ListResponseWrapper<RESPONSE> {
        this.lang = lang
        return this
    }

    private var code: String? = null
    fun code(code: String?): ListResponseWrapper<RESPONSE> {
        this.code = code
        return this
    }

    private var message: String? = null
    fun message(message: String?): ListResponseWrapper<RESPONSE> {
        this.message = message
        return this
    }

    private var data: List<RESPONSE>? = null
    fun data(data: List<RESPONSE>?): ListResponseWrapper<RESPONSE> {
        this.data = data
        return this
    }

    companion object {
        fun <RESPONSE0> create(): ListResponseWrapper<RESPONSE0> {
            return ListResponseWrapper()
        }
    }
}
