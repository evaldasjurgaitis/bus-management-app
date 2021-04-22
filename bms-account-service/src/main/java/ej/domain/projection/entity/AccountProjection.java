package ej.domain.projection.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "account_projection")
public class AccountProjection {

    @Id
    @Column(name = "aggregate_id", nullable = false)
    @NotNull
    private String aggregateId;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "credit_limit", nullable = false)
    private BigDecimal creditLimit;

    @NotNull
    @Column(name = "customer_aggregate_id", nullable = false)
    private String customerAggregateId;

    @Column(name = "time", nullable = false)
    @NotNull
    private ZonedDateTime time;

}
