package ej.domain.aggregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    private Long id;
    private String ticketNumber;
    private BigDecimal price;

}
