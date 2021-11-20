package sample.core.transfer

class PageResponseWrapper<RESPONSE> {
    private var success = true
    fun success(success: Boolean): PageResponseWrapper<RESPONSE> {
        this.success = success
        return this
    }

    private var lang: String? = null
    fun lang(lang: String?): PageResponseWrapper<RESPONSE> {
        this.lang = lang
        return this
    }

    private var code: String? = null
    fun code(code: String?): PageResponseWrapper<RESPONSE> {
        this.code = code
        return this
    }

    private var message: String? = null
    fun message(message: String?): PageResponseWrapper<RESPONSE> {
        this.message = message
        return this
    }

    private var totalPages = 0
    fun totalPages(totalPages: Int): PageResponseWrapper<RESPONSE> {
        this.totalPages = totalPages
        return this
    }

    private var totalElements: Long = 0
    fun totalElements(totalElements: Long): PageResponseWrapper<RESPONSE> {
        this.totalElements = totalElements
        return this
    }

    private var data: List<RESPONSE>? = null
    fun data(data: List<RESPONSE>?): PageResponseWrapper<RESPONSE> {
        this.data = data
        return this
    }

    companion object {
        fun <RESPONSE0> create(): PageResponseWrapper<RESPONSE0> {
            return PageResponseWrapper()
        }
    }
}
