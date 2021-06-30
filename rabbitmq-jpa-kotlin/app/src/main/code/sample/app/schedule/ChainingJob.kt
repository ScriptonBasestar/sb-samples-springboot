package sample.app.schedule

import org.apache.commons.lang3.time.DateUtils
import org.quartz.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import sample.core.util.loggerUtil
import java.util.*

@Component
class ChainingJob(
    @Autowired private val scheduler: Scheduler
) : Job {
    val log = loggerUtil()
    companion object {
        var stept: Int = 0
    }
    override fun execute(context: JobExecutionContext) {
        log.debug("chaining job start")

        println(context.mergedJobDataMap)
        val step = context.mergedJobDataMap["step"]
        val nextStep = step as Int + 1
        stept = step
        context.put("jobkey2-$step", "jobval2-$step")
        scheduler.rescheduleJob(
            context.trigger.key,
            TriggerBuilder.newTrigger()
                .withIdentity(TriggerKey.triggerKey("tr2","tg2"))
//                .forJob(context.jobDetail)
//                .withIdentity(TriggerKey.triggerKey("tr2-$nextStep","tg2-$nextStep"))
                .startAt(DateUtils.addSeconds(Date(), 8))
                .usingJobData("triggerkey2-$nextStep", "triggerval2-$nextStep")
                .usingJobData("step", nextStep)
                .build()
        )
        log.debug("chaining job end")
    }
}
