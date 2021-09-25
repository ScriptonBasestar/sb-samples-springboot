package sample.domain.jpa.config;

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
@EntityScan(basePackages = "sample.domain.jpa.model")
@EnableJpaRepositories(basePackages = "sample.domain.jpa.repository")
public class DomainJpaConfig {

//    @Bean
//    fun dataSource(): DataSource {
//        val builder = EmbeddedDatabaseBuilder()
//        return builder.setType(EmbeddedDatabaseType.H2).build()
//    }

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

//    @Bean
//    fun transactionManager(entityManagerFactory: EntityManagerFactory?): PlatformTransactionManager {
//        val txManager = JpaTransactionManager()
//        txManager.entityManagerFactory = entityManagerFactory
//        return txManager
//    }
}
