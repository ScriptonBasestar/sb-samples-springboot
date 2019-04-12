package com.example.demo

import io.github.cdimascio.swagger.Validate


//https://github.com/cdimascio/openapi-spring-webflux-validator
//val validate = Validate.configure("api.yaml")
data class MyError(val id: String, val messages: List<String>)
val validate = Validate.configure("static/api.json") { status, messages ->
	MyError(status.name, messages)
}