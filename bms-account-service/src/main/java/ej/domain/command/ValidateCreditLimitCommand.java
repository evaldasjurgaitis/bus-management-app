package ej.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateCreditLimitCommand implements AccountCommand {

    private Boolean isValidCreditLimit;
    private String bookingAggregateId;

}
