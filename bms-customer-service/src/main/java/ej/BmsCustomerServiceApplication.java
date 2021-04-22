package ej;

import ej.config.CustomerServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({CustomerServiceConfiguration.class})
public class BmsCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BmsCustomerServiceApplication.class, args);
	}

}
