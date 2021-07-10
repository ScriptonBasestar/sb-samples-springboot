package sample.member.app.web.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import sample.member.core.exception.BusinessException;
import sample.member.core.transfer.EmptyResponseWrapper;
import sample.member.core.transfer.ListResponseWrapper;
import sample.member.core.transfer.SimpleFieldError;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ExceptionController {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ListResponseWrapper<SimpleFieldError> MethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		return ListResponseWrapper.<SimpleFieldError>create()
				.data(processFieldErrors(ex.getBindingResult().getFieldErrors()))
				.success(false);
	}

	private List<SimpleFieldError> processFieldErrors(List<FieldError> fieldErrors) {
		return fieldErrors.stream()
				.map(fieldError -> new SimpleFieldError(
								fieldError.getField(),
								messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()),
								fieldError.getCode()
						)
				).collect(Collectors.toList());
	}

	@ExceptionHandler(BusinessException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public EmptyResponseWrapper BusinessException(BusinessException ex) {
		return EmptyResponseWrapper.create()
				.message(ex.getMessage())
				.success(false);
	}


}
