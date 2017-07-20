package org.scripton.core.exception;

/**
 * @author archmagece
 * @since 2017-08-25
 *
 * 그냥 진행하다보면 될 수 있는 오류인 경우 사용
 *
 * 외부 API호출안되는경우. timeout, 서버일시중지 등.
 */
public class SBContinueException extends RuntimeException {
	public SBContinueException(String message){
		super(message);
	}
	public SBContinueException(String message, Throwable e){
		super(message, e);
	}
}
