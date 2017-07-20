package org.scripton.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

/**
 * @author archmagece
 * @since 2017-08-23
 */
@Configuration
@EnableAsync
@EnableScheduling
public class AppConfig {
	@Bean
	public TaskScheduler taskScheduler() {
		return new ConcurrentTaskScheduler(); //single threaded by default
	}

}
