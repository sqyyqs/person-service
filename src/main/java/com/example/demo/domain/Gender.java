package com.example.demo.domain;

import javax.annotation.Nullable;
import java.util.Arrays;

public enum Gender {
    MALE, FEMALE;

    @Nullable
    public static Gender getByValue(@Nullable String value) {
        if (value == null) {
            return null;
        }
        return Arrays.stream(values())
                .filter(gender -> gender.name().equals(value))
                .findFirst()
                .orElse(null);
    }
}
