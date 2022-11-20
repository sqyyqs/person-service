package com.example.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateMessageStatusDto {
    private final long messageId;
    private final boolean read;
    private final long personId;

    @JsonCreator
    @JsonIgnoreProperties(ignoreUnknown = true)
    public UpdateMessageStatusDto(@JsonProperty("messageId") long messageId,
                                  @JsonProperty("read") boolean read,
                                  @JsonProperty("personId") long personId) {
        this.messageId = messageId;
        this.read = read;
        this.personId = personId;
    }

    public long getMessageId() {
        return messageId;
    }

    public boolean isRead() {
        return read;
    }

    public long getPersonId() {
        return personId;
    }

    @Override
    public String toString() {
        return "UpdateMessageStatusDto{" +
                "messageId=" + messageId +
                ", read=" + read +
                ", personId=" + personId +
                '}';
    }
}
