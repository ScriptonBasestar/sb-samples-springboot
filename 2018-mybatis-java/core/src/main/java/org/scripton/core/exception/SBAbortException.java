package org.scripton.core.exception;

/**
 * @author archmagece
 * @since 2017-08-25
 */
public class SBAbortException extends RuntimeException {
	public SBAbortException(String message){
		super(message);
	}
	public SBAbortException(String message, Throwable e){
		super(message, e);
	}
}
