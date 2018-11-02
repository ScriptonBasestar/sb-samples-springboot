package sample.member.core.exception;

public class BusinessException extends BaseException {
	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable e) {
		super(message, e);
	}
}

