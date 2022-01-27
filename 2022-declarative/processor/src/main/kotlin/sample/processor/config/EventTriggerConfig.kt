package sample.processor.config

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.context.annotation.Configuration
import java.lang.reflect.Method


@Configuration
class EventTriggerConfig {
    @Around("@annotation(EventTrigger)")
    @Throws(Throwable::class)
    fun triggerExecution(joinPoint: ProceedingJoinPoint): Any {
        val methodName: String = joinPoint.signature.name
        val methodSignature: MethodSignature = joinPoint.signature as MethodSignature
        val method: Method = methodSignature.method.let { method ->
            if (method.declaringClass.isInterface) {
                joinPoint.target.javaClass.getDeclaredMethod(methodName, *method.parameterTypes)
            } else {
                method
            }
        }
        method.getAnnotation(EventTrigger::class.java).let {
            if (it.pre) {
                // run
            }
            if (it.post) {
                // run
            }
        }
        return joinPoint.proceed()
    }
}
