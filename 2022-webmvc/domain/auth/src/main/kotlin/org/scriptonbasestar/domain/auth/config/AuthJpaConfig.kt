package org.scriptonbasestar.domain.auth.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaAuditing
// @EnableSpringDataWebSupport
@EnableTransactionManagement
@EntityScan(basePackages = ["org.scriptonbasestar.domain.auth", "org.scriptonbasestar.base"])
@EnableJpaRepositories(basePackages = ["org.scriptonbasestar.domain.auth"])
@ComponentScan("org.scriptonbasestar.domain.auth")
class AuthJpaConfig {
//    @Bean
//    fun dataSource(): DataSource {
//        val builder = EmbeddedDatabaseBuilder()
//        return builder.setType(EmbeddedDatabaseType.H2).build()
//    }
//
//    @Bean
//    fun entityManagerFactory(dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
//        val vendorAdapter = HibernateJpaVendorAdapter()
//        vendorAdapter.setGenerateDdl(true)
//        val factory = LocalContainerEntityManagerFactoryBean()
//        factory.jpaVendorAdapter = vendorAdapter
//        factory.setPackagesToScan("sample.domain.model")
//        factory.dataSource = dataSource
//        return factory
//    }
//
//    @Bean
//    fun transactionManager(entityManagerFactory: EntityManagerFactory?): PlatformTransactionManager {
//        val txManager = JpaTransactionManager()
//        txManager.entityManagerFactory = entityManagerFactory
//        return txManager
//    }
}
