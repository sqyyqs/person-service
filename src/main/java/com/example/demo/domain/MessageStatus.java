package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageStatus {
    private final String uuid;

    @JsonCreator
    public MessageStatus(@JsonProperty("uuid") String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "MessageStatus{" +
                "uuid='" + uuid + '\'' +
                '}';
    }
}
