package org.scriptonbasestar.core.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.URLEncoder

inline fun <reified T> T.loggerUtil(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}

fun String.urlEncode() = URLEncoder.encode(this, "UTF-8")
