package com.example.demo.service;

import com.example.demo.domain.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getByName(String name) {
        return personRepository.getByName(name);
    }

    public Collection<Person> getAll() {
        return personRepository.getAll();
    }

    public void addPerson(Person person) {
        personRepository.addPerson(person);
    }

    public void removePerson(String name) {
        personRepository.removePerson(name);
    }

    public boolean updatePerson(Person person) {
        return personRepository.updatePerson(person);
    }
}
