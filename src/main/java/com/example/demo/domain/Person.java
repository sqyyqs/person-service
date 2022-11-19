package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
    private final double height;
    private final double weight;
    private final String name;
    private final long age;

    @JsonCreator
    public Person(@JsonProperty("height") double  height, @JsonProperty("weight") double weight,
                  @JsonProperty("name") String name, @JsonProperty("age") long age) {
        this.height = height;
        this.weight = weight;
        this.name = name;
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public long getAge() {
        return age;
    }
}
