appender("STDOUT", ConsoleAppender) {
	encoder(PatternLayoutEncoder) {
		pattern = "%d{HH:mm:ss.SSS} %-5level [%thread] %logger{36}.%method : %msg%n"
	}
}

appender("FILE", FileAppender) {
	file = "logs/testFile.log"
	append = true
	encoder(PatternLayoutEncoder) {
		pattern = "%d{HH:mm:ss.SSS} %-5level [%thread] %logger{36}.%method : %msg%n"
	}
}

logger("hello", INFO)

root(DEBUG, ["STDOUT"])