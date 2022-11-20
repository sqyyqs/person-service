package com.example.demo.domain.dto;

import com.example.demo.domain.Gender;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nullable;
import java.util.Objects;

public class PersonDto {
    private final double height;
    private final double weight;
    private final String name;
    private final long age;

    @Nullable
    private final Gender gender;

    @Nullable
    private final Long id;

    @JsonCreator
    @JsonIgnoreProperties(ignoreUnknown = true)
    public PersonDto(@JsonProperty("height") double  height,
                     @JsonProperty("weight") double weight,
                     @JsonProperty("name") String name,
                     @JsonProperty("age") long age,
                     @Nullable @JsonProperty("id") Long id,
                     @Nullable @JsonProperty("gender") String genderValue) {
        this.height = height;
        this.weight = weight;
        this.name = Objects.requireNonNull(name, "Name can't be null.");
        this.age = age;
        this.id = id;
        this.gender = Gender.getByValue(genderValue);
    }

    public double getHeight() {
        return height;
    }

    @Nullable
    public Long getId() {
        return id;
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

    @Nullable
    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "height=" + height +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", id=" + id +
                '}';
    }
}
