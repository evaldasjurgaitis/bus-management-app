package ej.domain.event.customer;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "ej.domain.aggregate.Customer")
public class CustomerEvent implements Event {
}