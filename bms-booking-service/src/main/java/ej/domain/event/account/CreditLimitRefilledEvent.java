package ej.domain.event.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditLimitRefilledEvent extends AccountEvent {

    private String accountAggregateId;
    private BigDecimal amount;

}
