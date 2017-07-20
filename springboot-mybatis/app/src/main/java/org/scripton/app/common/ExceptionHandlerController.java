package org.scripton.app.common;

import org.scripton.core.reqres.SBEmptyResponseWrapper;
import org.scripton.core.reqres.ValidationErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Slf4j
//@ControllerAdvice
//@Profile({"real", "dev", "devalpha"})
public class ExceptionHandlerController {

	@Autowired
	private MessageSource messageSource;

	//The add() method is omitted.

	private ValidationErrorDto processFieldErrors(List<FieldError> fieldErrors) {
		ValidationErrorDto dto = new ValidationErrorDto();

		for (FieldError fieldError: fieldErrors) {
			String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
			dto.addFieldError(fieldError.getField(), localizedErrorMessage);
		}

		return dto;
	}

	private String resolveLocalizedErrorMessage(FieldError fieldError) {
		Locale currentLocale =  LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);

		//If the message was not found, return the most accurate field error code instead.
		//You can remove this check if you prefer to get the default error message.
		if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
			String[] fieldErrorCodes = fieldError.getCodes();
			localizedErrorMessage = fieldErrorCodes[0];
		}

		return localizedErrorMessage;
	}

	@ExceptionHandler({MissingServletRequestParameterException.class})
	@ResponseBody
	public SBEmptyResponseWrapper MissingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest request) throws IOException {
		log.debug("ExceptionHandlerController.MissingServletRequestParameterException : {},  {}", e.getMessage(), request.getParameterMap().toString());
		return SBEmptyResponseWrapper.create().fail().message("MissingServletRequestParameterException");
	}

	@ExceptionHandler({ServletRequestBindingException.class})
	@ResponseBody
	public SBEmptyResponseWrapper ServletRequestBindingException(ServletRequestBindingException e, HttpServletRequest request) throws IOException {
		log.debug("ExceptionHandlerController.ServletRequestBindingException : {},  {}", e.getMessage(), request.getParameterMap().toString());
		return SBEmptyResponseWrapper.create().fail().message("ServletRequestBindingException");
	}

	@ExceptionHandler({TypeMismatchException.class})
	@ResponseBody
	public SBEmptyResponseWrapper TypeMismatchException(TypeMismatchException e, HttpServletRequest request) throws IOException {
		log.debug("ExceptionHandlerController.TypeMismatchException : {},  {}", e.getMessage(), request.getParameterMap().toString());
		return SBEmptyResponseWrapper.create().fail().message("TypeMismatchException");
	}

	@ExceptionHandler({HttpMessageNotReadableException.class})
	@ResponseBody
	public SBEmptyResponseWrapper HttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) throws IOException {
		log.debug("ExceptionHandlerController.HttpMessageNotReadableException : {},  {}", e.getMessage(), request.getParameterMap().toString());
		return SBEmptyResponseWrapper.create().fail().message("HttpMessageNotReadableException");
	}

	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseBody
	public SBEmptyResponseWrapper MethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) throws IOException {
		log.debug("ExceptionHandlerController.MethodArgumentNotValidException : {},  {}", e.getMessage(), request.getParameterMap().toString());
		return SBEmptyResponseWrapper.create().fail().message("MethodArgumentNotValidException");
	}

	@ExceptionHandler({MissingServletRequestPartException.class})
	@ResponseBody
	public SBEmptyResponseWrapper MissingServletRequestPartException(MissingServletRequestPartException e, HttpServletRequest request) throws IOException {
		log.debug("ExceptionHandlerController.MissingServletRequestPartException : {},  {}", e.getMessage(), request.getParameterMap().toString());
		return SBEmptyResponseWrapper.create().fail().message("MissingServletRequestPartException");
	}

	@ExceptionHandler({Exception.class})
	@ResponseBody
	public SBEmptyResponseWrapper Exception(Exception e, HttpServletRequest request) {
		log.error("ExceptionHandlerController.Exception : {},  {}", e.getMessage(), request.getParameterMap().toString());
		return SBEmptyResponseWrapper.create().fail().message("알 수 없는 오류입니다. 관리자에게 문의해주세요");
	}
}