package ej.domain.event.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TakenPaymentEvent extends AccountEvent {

    private BigDecimal amountTotal;
    private String orderAggregateId;

}
