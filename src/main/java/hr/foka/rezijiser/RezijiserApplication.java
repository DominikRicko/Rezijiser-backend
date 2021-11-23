package hr.foka.rezijiser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories("package path") uncomment when package with repositories exists.
public class RezijiserApplication {

	public static void main(String[] args) {
		SpringApplication.run(RezijiserApplication.class, args);
	}

}
