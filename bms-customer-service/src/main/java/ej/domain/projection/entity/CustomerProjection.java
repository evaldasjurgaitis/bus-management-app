package ej.domain.projection.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "customer_projection")
public class CustomerProjection {

    @Id
    @Column(name = "aggregate_id", nullable = false)
    @NotNull
    private String aggregateId;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "surname", nullable = false)
    private String surname;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "time", nullable = false)
    @NotNull
    private ZonedDateTime time;

}
