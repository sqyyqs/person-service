package com.example.demo.domain;

import org.springframework.lang.Nullable;

import java.util.Arrays;

public enum MessageType {
    TELEGRAM, SMS;

    @Nullable
    public static MessageType getByValue(@Nullable String value) {
        if (value == null) {
            return null;
        }
        return Arrays.stream(values())
                .filter(msgType -> msgType.name().equals(value))
                .findFirst()
                .orElse(null);
    }
}
