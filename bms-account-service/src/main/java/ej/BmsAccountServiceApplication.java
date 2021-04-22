package ej;

import ej.config.AccountServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AccountServiceConfiguration.class})
public class BmsAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BmsAccountServiceApplication.class, args);
	}

}
