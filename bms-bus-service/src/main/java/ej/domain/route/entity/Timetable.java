package ej.domain.route.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "timetable")
@NoArgsConstructor
@Data
public class Timetable {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    @NotNull
    private Long id;

    @ManyToOne
    @JoinColumn(name="route_id", nullable=false)
    private Route route;

    @Column(name = "hour")
    private Long hour;

    @Column(name = "minute")
    private Long minute;

}
