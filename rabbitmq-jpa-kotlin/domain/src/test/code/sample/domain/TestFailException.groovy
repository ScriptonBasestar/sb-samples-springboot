package sample.domain

class TestFailException extends RuntimeException {
    TestFailException(String message) {
        super(message)
    }

    TestFailException(String message, Throwable t) {
        super(message, t)
    }
}
