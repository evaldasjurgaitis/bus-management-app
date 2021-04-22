package ej.domain.event.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreditExceededEvent extends AccountEvent {

    private String orderAggregateId;

}
