package com.example.demo.domain;

import com.example.demo.utils.MessageUtils;

import java.util.Objects;

public class Message {
    private final long fromId;
    private final long toId;
    private final String message;
    private final boolean read;
    private final long messageId;

    public Message(long fromId, long toId, String message, boolean read, long messageId) {
        this.fromId = fromId;
        this.toId = toId;
        this.message = Objects.requireNonNull(MessageUtils.messageFormatter(message), "Message can't be null.");
        this.read = read;
        this.messageId = messageId;
    }

    public long getMessageId() {
        return messageId;
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

    public boolean isRead() {
        return read;
    }

    @Override
    public String toString() {
        return "Message{" +
                "fromId=" + fromId +
                ", toId=" + toId +
                ", message='" + message + '\'' +
                ", read=" + read +
                ", messageId=" + messageId +
                '}';
    }

}
