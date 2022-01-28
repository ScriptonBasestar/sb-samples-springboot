package sample.spring.streotype

import org.springframework.stereotype.Component

@Target(AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class ProcessorService {
//    @AliasFor(annotation = Component::class)
//    val value: String = ""
}
