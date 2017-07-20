package org.scripton.core.exception;

/**
 * @author archmagece
 * @since 2017-08-25
 */
public class SBExternalException extends RuntimeException {
	public SBExternalException(String message){
		super(message);
	}
	public SBExternalException(String message, Throwable e){
		super(message, e);
	}
}
