package sample.member.domain.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EnableSpringDataWebSupport
@EnableTransactionManagement
@EntityScan(basePackages = "sample.member.domain.model")
@EnableJpaRepositories(basePackages = "sample.member.domain.repository")
public class DomainConfig {
}
