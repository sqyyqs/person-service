package com.example.demo.domain.dto;

import com.example.demo.utils.MessageUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class MessageDto {
    private final long fromId;
    private final long toId;
    private final String message;

    @JsonCreator
    @JsonIgnoreProperties(ignoreUnknown = true)
    public MessageDto(@JsonProperty("fromId") long fromId,
                      @JsonProperty("toId") long toId,
                      @JsonProperty("message") String message) {
        this.fromId = fromId;
        this.toId = toId;
        this.message = Objects.requireNonNull(MessageUtils.messageFormatter(message), "Message can't be null.");
    }

    public long getFromId() {
        return fromId;
    }

    public long getToId() {
        return toId;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "fromId=" + fromId +
                ", toId=" + toId +
                ", message='" + message + '\'' +
                '}';
    }
}
