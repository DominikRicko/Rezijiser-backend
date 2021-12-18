package hr.foka.rezijiser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("hr.foka.rezijiser.persistence.repository")
@EnableConfigurationProperties(ApplicationProperties.class)
public class RezijiserApplication {

	public static void main(String[] args) {
		SpringApplication.run(RezijiserApplication.class, args);
	}

}