package br.com.douglasog87.event.processor;

import br.com.douglasog87.event.config.EventConfig;
import br.com.douglasog87.event.domain.Payload;
import br.com.douglasog87.event.event.DomainEvent;
import br.com.douglasog87.event.event.EventType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@AllArgsConstructor
public class EventProcessor {

    private EventConfig eventConfig;
    private RabbitTemplate rabbitTemplate;

    @EventListener(DomainEvent.class)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT,fallbackExecution = true)
    public <T extends Payload,E extends EventType> void onEvent(DomainEvent<T,E> event ) {

        StringBuilder routerKey = new StringBuilder()
                .append( event.getPayload().domainName() ).append(".")
                .append( event.getType().name().toLowerCase() );

        rabbitTemplate.convertAndSend(
                eventConfig.getEventTopicName(),
                routerKey.toString(),
                event.withApplication( eventConfig.getApplication() )
        );
    }
}
