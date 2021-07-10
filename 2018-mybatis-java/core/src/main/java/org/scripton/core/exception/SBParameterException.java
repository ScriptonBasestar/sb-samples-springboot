package org.scripton.core.exception;

/**
 * @author archmagece
 * @since 2017-08-25
 */
public class SBParameterException extends RuntimeException {
	public SBParameterException(String message){
		super(message);
	}
	public SBParameterException(String message, Throwable e){
		super(message, e);
	}
}
