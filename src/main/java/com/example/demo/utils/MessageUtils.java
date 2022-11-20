package com.example.demo.utils;

import org.springframework.util.StringUtils;

import javax.annotation.Nullable;

public final class MessageUtils {

    private MessageUtils() {
    }

    @Nullable
    public static String messageFormatter(@Nullable String value) {
        return StringUtils.trimAllWhitespace(value);
    }
}
