package sample.app.schedule

import org.quartz.Scheduler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.scriptonbasestar.core.util.loggerUtil
import java.text.SimpleDateFormat
import java.util.*

@Component
class CronService(
    @Autowired private val scheduler: Scheduler,
) {

    val log = loggerUtil()

    private val dateFormat = SimpleDateFormat("HH:mm:ss")

    //    @Scheduled(cron = "0 0 9 * * *", zone = "Asia/Seoul")
//    @Scheduled(cron = "* * * * * *", zone = "Asia/Seoul")
//    fun reportCurrentTime() {
//        log.info("The time is now {}", dateFormat.format(Date()))
//    }
}
