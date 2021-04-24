package ej;

import ej.config.SeatReservationServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({SeatReservationServiceConfiguration.class})
public class BmsBusServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BmsBusServiceApplication.class, args);
    }

}
