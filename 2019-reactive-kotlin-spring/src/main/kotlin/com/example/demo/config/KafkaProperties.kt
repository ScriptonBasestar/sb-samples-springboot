package com.example.demo.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "kafka")
data class KafkaProperties(
		var brokerList: String = "",
		var groupName: String = "",
		var clientName: String = "",
		var topicList: String = "",
		var metadataAge: Long = 0,
		var timeOut: Int = 0
)