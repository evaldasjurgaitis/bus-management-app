package ej.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefillCreditLimitCommand implements AccountCommand {

    private String accountAggregateId;
    private BigDecimal creditAmount;

}
