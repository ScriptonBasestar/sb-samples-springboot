package sample.app.schedule

import org.apache.commons.lang3.time.DateUtils
import org.quartz.*
import org.quartz.impl.matchers.GroupMatcher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct

@Component
class ScheduleInit(
    @Autowired private val scheduler: Scheduler,
) {

    @PostConstruct
    fun runSchedule() {
//        let {
//            val job = JobBuilder
//                .newJob(EmptyJob::class.java)
//                .withIdentity(JobKey.jobKey("jk1", "jg1"))
//                .usingJobData("jobkey", "jobval")
//                .storeDurably()
//                .build()
// //        scheduler.addJob(job, true)
//            val trigger = TriggerBuilder
//                .newTrigger()
// //            .forJob(JobKey.jobKey("jk1", "jg1"))
// //            .forJob(job)
//                .withIdentity(TriggerKey.triggerKey("tr1", "tg1"))
//                .startAt(DateUtils.addSeconds(Date(), 10))
//                .build()
//            scheduler.scheduleJob(job, trigger)
//        }
        let {
            val step = 1
            val job = JobBuilder
                .newJob(ChainingJob::class.java)
                .withIdentity("jk2", "jg2")
                .usingJobData("jobkey2-$step", "jobval2-$step")
                .storeDurably()
                .build()
//        scheduler.addJob(job, true)
            val trigger = TriggerBuilder
                .newTrigger()
//            .forJob(JobKey.jobKey("jk2", "jg2"))
//            .forJob(job)
                .withIdentity("tr2", "tg2")
//                .withIdentity(TriggerKey.triggerKey("tr2-$step", "tg2-$step"))
                .startAt(DateUtils.addSeconds(Date(), 5))
                .usingJobData("triggerkey2-$step", "triggerval2-$step")
                .usingJobData("step", step)
                .build()
            scheduler.scheduleJob(job, trigger)
        }
        println("jfjfwoijiowefjiojfwoijiowefjiojfwoijiowefjiojfwoijiowefjiojfwoijiowefjiowoijiowefjio")
        println(scheduler.jobGroupNames)
        println(scheduler.getJobKeys(GroupMatcher.anyGroup()))
        scheduler.start()
    }
}
