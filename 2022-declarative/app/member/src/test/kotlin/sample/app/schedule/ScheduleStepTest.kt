package sample.app.schedule

import org.awaitility.Awaitility
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.quartz.Scheduler
import org.quartz.TriggerKey
import org.quartz.impl.matchers.GroupMatcher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.Duration
import java.time.temporal.ChronoUnit

@SpringBootTest
class ScheduleStepTest(
    @Autowired private val scheduler: Scheduler
) {

    @BeforeEach
    fun beforeAll() {
        println("before all")
    }

    @Test
    fun `test scheduled test`() {
        // step0
        Awaitility.await().atMost(Duration.of(10, ChronoUnit.SECONDS)).until {
            ChainingJob.stepCounter.get() > 0
        }
        println("ajfiowjfiwjofiwijefijweof ${scheduler.getTriggerKeys(GroupMatcher.anyGroup())}")
//        println("ajfiowjfiwjofiwijefijweof ${i++} ${scheduler.getTrigger(TriggerKey.triggerKey("tr2-$i", "tg2-$i")).jobDataMap}")

        // step1
        Awaitility.await().atMost(Duration.of(10, ChronoUnit.SECONDS)).until {
            ChainingJob.stepCounter.get() > 1
        }
        println("ajfiowjfiwjofiwijefijweof ${scheduler.getTriggerKeys(GroupMatcher.anyGroup())}")
//        println("ajfiowjfiwjofiwijefijweof ${i++} ${scheduler.getTrigger(TriggerKey.triggerKey("tr2-$i", "tg2-$i")).jobDataMap}")

        // step2
        Awaitility.await().atMost(Duration.of(10, ChronoUnit.SECONDS)).until {
            ChainingJob.stepCounter.get() > 2
        }
        println("ajfiowjfiwjofiwijefijweof ${scheduler.getTriggerKeys(GroupMatcher.anyGroup())}")
//        println("ajfiowjfiwjofiwijefijweof ${i++} ${scheduler.getTrigger(TriggerKey.triggerKey("tr2-$i", "tg2-$i")).jobDataMap}")

        // step3
        Awaitility.await().atMost(Duration.of(15, ChronoUnit.SECONDS)).until {
            ChainingJob.stepCounter.get() > 3
        }
        println("ajfiowjfiwjofiwijefijweof ${scheduler.getTriggerKeys(GroupMatcher.anyGroup())}")
//        println("ajfiowjfiwjofiwijefijweof ${i++} ${scheduler.getTrigger(TriggerKey.triggerKey("tr2-$i", "tg2-$i")).jobDataMap}")

//        scheduler.unscheduleJob(TriggerKey.triggerKey("tr2-$step, "tg2-$step"))
        scheduler.unscheduleJob(TriggerKey.triggerKey("tr2", "tg2"))
        println("ajfiowjfiwjofiwijefijweof ${scheduler.getTriggerKeys(GroupMatcher.anyGroup())}")
//        scheduler.unscheduleJob(TriggerKey.triggerKey("tr2", "tg2"))
//        scheduler.rescheduleJob(
//            TriggerKey.triggerKey("tr1", "tg1"),
//            TriggerBuilder.newTrigger()
//                .startAt()
//                .build()
//        )
    }
}
