package ej.api.response;

import ej.domain.aggregate.Account;
import ej.domain.projection.entity.AccountProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccountResponse {

    private String aggregateId;
    private String name;
    private BigDecimal creditLimit;
    private String customerAggregateId;

    public static AccountResponse fromDomain(Account account, String aggregateId) {
        return AccountResponse.builder()
                .name(account.getName())
                .creditLimit(account.getCreditLimit())
                .customerAggregateId(account.getCustomerAggregateId())
                .aggregateId(aggregateId).build();
    }

    public static AccountResponse fromProjection(AccountProjection accountProjection) {
        return AccountResponse.builder()
                .name(accountProjection.getName())
                .creditLimit(accountProjection.getCreditLimit())
                .customerAggregateId(accountProjection.getCustomerAggregateId())
                .aggregateId(accountProjection.getAggregateId()).build();
    }

}
