package sample.member.core.exception;

public class DataNotFoundException extends BaseException {
	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(String message, Throwable e) {
		super(message, e);
	}
}
