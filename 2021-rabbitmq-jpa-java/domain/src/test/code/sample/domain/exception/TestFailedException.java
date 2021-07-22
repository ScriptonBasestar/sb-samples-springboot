package sample.domain.exception;

public class TestFailedException extends RuntimeException {
    public TestFailedException(String message) {
        super(message);
    }

    public TestFailedException(String message, Throwable t) {
        super(message, t);
    }
}
