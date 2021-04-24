package ej.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Timetable {

    private Long hour;
    private Long minute;

    public Timetable(ej.domain.route.entity.Timetable timetable) {
        this.hour = timetable.getHour();
        this.minute = timetable.getMinute();
    }

}
