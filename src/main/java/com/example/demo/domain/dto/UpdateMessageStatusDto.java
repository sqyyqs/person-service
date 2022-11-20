package com.example.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateMessageStatusDto {
    private final long messageId;
    private final boolean read;

    @JsonCreator
    @JsonIgnoreProperties(ignoreUnknown = true)
    public UpdateMessageStatusDto(@JsonProperty("messageId") long messageId,
                                  @JsonProperty("read") boolean read) {
        this.messageId = messageId;
        this.read = read;
    }

    public long getMessageId() {
        return messageId;
    }

    public boolean isRead() {
        return read;
    }

    @Override
    public String toString() {
        return "UpdateMessageStatusDto{" +
                "messageId=" + messageId +
                ", read=" + read +
                '}';
    }
}
