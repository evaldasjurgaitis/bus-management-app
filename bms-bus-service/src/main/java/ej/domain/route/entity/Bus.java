package ej.domain.route.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "buses")
@NoArgsConstructor
@Data
public class Bus {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    @NotNull
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "seatsNo")
    private Long seatsNo;

}
