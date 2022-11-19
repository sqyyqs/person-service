package com.example.demo.repository;

import com.example.demo.domain.Person;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PersonRepository {
    private final Map<String, Person> persons = new ConcurrentHashMap<>();

    public PersonRepository() {
        persons.put("Joe", new Person(178, 67, "Joe", 21));
        persons.put("Misha", new Person(181, 79.9, "Misha", 25));
    }

    public Person getByName(String name) {
        return persons.get(name);
    }

    public Collection<Person> getAll() {
        return persons.values();
    }

    public void addPerson(Person person) {
        persons.put(person.getName(), person);
    }

    public void removePerson(String name) {
        persons.remove(name);
    }

    public boolean updatePerson(Person person) {
        String personName = person.getName();
        if (persons.containsKey(personName)) {
            persons.put(personName, person);
            return true;
        }
        return false;
    }
}
