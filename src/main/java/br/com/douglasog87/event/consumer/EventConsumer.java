package br.com.douglasog87.event.consumer;

import br.com.douglasog87.event.domain.Payload;
import br.com.douglasog87.event.event.DomainEvent;
import br.com.douglasog87.event.event.EventType;

public interface EventConsumer<T extends Payload,E extends EventType> {

    void consume(DomainEvent<T, E> event) throws Exception;
}