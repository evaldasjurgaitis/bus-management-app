package ej.api.request;

import ej.api.response.Passenger;
import ej.api.response.Trip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookingItemRequest {

    private Passenger passenger;
    private Trip trip;

}
