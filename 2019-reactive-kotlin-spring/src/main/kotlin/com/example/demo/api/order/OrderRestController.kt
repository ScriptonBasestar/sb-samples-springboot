//package com.example.demo.api.order
//
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.http.HttpStatus
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RequestMethod
//import org.springframework.web.bind.annotation.ResponseBody
//import org.springframework.web.bind.annotation.RestController
//import reactor.core.publisher.Mono
//
//@RestController
//class OrderRestController(
//		@Autowired
//		private val orderService: OrderService
//) {
//
//	@RequestMapping("/empty", method = [RequestMethod.GET])
//	@ResponseBody
//	fun empty(): Mono<OrderHttpResponse> = orderService.empty()
//
//}