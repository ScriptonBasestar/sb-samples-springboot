package sample.app.trigger.schedule

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import sample.core.util.loggerUtil
import java.text.SimpleDateFormat
import java.util.*


@Component
class ScheduleService(
) {

    val log = loggerUtil()

    private val dateFormat = SimpleDateFormat("HH:mm:ss")

    //    @Scheduled(cron = "0 0 9 * * *", zone = "Asia/Seoul")
    @Scheduled(cron = "* * * * * *", zone = "Asia/Seoul")
    fun reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(Date()))
    }
}
