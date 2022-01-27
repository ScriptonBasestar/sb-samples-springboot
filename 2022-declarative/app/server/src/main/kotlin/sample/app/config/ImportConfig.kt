package sample.app.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import sample.domain.jpa.config.DomainJpaConfig

@Configuration
@Import(DomainJpaConfig::class)
class ImportConfig
