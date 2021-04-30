package ej.domain.event.account;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "ej.domain.aggregate.Account")
public class AccountEvent implements Event {
}
