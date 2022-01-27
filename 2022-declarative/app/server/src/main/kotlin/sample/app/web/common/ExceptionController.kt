package sample.app.web.common

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import sample.core.exception.BusinessException
import sample.core.transfer.EmptyResponseWrapper
import sample.core.transfer.ListResponseWrapper
import sample.core.transfer.SimpleFieldError

@ControllerAdvice
class ExceptionController {

    @Autowired
    private lateinit var messageSource: MessageSource

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun fnMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ListResponseWrapper<SimpleFieldError> =
        ListResponseWrapper.create<SimpleFieldError>()
            .data(processFieldErrors(ex.bindingResult.fieldErrors))
            .success(false)

    private fun processFieldErrors(fieldErrors: List<FieldError>): List<SimpleFieldError> =
        fieldErrors.map { fieldError ->
            SimpleFieldError(
                fieldError.field,
                messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()),
                fieldError.code!!
            )
        }

    @ExceptionHandler(BusinessException::class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun fnBusinessException(ex: BusinessException): EmptyResponseWrapper =
        EmptyResponseWrapper.create()
            .message(ex.message)
            .success(false)
}
