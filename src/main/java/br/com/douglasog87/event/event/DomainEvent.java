package br.com.douglasog87.event.event;

import br.com.douglasog87.event.domain.Payload;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder(builderClassName = "Builder")
@Wither
@Value
@JsonDeserialize(builder = DomainEvent.Builder.class)
public class DomainEvent<T extends Payload,E extends EventType> {

    @lombok.Builder.Default
    UUID uuid = UUID.randomUUID();
    @lombok.Builder.Default
    LocalDateTime created = LocalDateTime.now();

    String application;

    T payload;
    E type;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder<T extends Payload,E extends EventType> {}
}
