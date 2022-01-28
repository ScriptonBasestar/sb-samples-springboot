package sample.processor.config


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class EventTrigger(
    val pre: Boolean = true,
    val post: Boolean = true,
)
