package ej;

import ej.config.BookingServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({BookingServiceConfiguration.class})
public class BmsBookingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BmsBookingServiceApplication.class, args);
    }

}
