package sample.spring.streotype

import org.springframework.stereotype.Component


@Target(AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class DomainService {
//    @AliasFor(annotation = Component::class)
//    val value: String = ""
}
