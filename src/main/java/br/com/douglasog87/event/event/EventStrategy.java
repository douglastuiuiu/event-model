package br.com.douglasog87.event.event;

import br.com.douglasog87.event.domain.Payload;

public interface EventStrategy {
    <T extends Payload,E extends EventType> DomainEvent newInstance(T payload);
}
