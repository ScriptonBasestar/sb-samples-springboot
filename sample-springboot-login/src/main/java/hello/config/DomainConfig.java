package hello.config;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author archmagece
 * @date 2017-05-12
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"hello.domain.repository"})
public class DomainConfig {

	@Bean
	public ServletRegistrationBean h2ServletRegistration() {
		ServletRegistrationBean bean = new ServletRegistrationBean(new WebServlet());
		bean.addUrlMappings("/console/*");
		return bean;
	}

}
