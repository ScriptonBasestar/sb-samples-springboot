package org.scriptonbasestar.domain.member.exception

class TestFailedException : RuntimeException {
    constructor(message: String?) : super(message) {}
    constructor(message: String?, t: Throwable?) : super(message, t) {}
}
