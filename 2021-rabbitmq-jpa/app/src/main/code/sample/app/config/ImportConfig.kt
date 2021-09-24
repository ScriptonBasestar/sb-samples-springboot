package sample.app.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import sample.domain.config.DomainConfig

@Configuration
@Import(DomainConfig::class)
class ImportConfig
