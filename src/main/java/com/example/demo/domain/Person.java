package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
    private final double height;
    private final double weight;
    private final String name;
    private final long age;
    private final long id;

    public Person(double height, double weight, String name, long age, long id) {
        this.height = height;
        this.weight = weight;
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    @JsonProperty("fullName")
    public String getName() {
        return name;
    }

    public long getAge() {
        return age;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "height=" + height +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
