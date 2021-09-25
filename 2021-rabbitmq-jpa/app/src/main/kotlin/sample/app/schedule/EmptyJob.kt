package sample.app.schedule

import org.quartz.Job
import org.quartz.JobExecutionContext
import org.springframework.stereotype.Component
import sample.core.util.loggerUtil

@Component
class EmptyJob : Job {
    val log = loggerUtil()
    override fun execute(context: JobExecutionContext?) {
        log.debug("EmptyJob start")

        log.debug("EmptyJob end")
    }
}
