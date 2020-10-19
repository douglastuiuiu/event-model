package br.com.douglasog87.event.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
public interface Payload {
    default String domainName() {
        return this.getClass().getSimpleName().toLowerCase();
    }
}
