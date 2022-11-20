package com.example.demo.service;

import com.example.demo.domain.Person;
import com.example.demo.domain.dto.PersonDto;
import com.example.demo.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getById(long id) {
        return personRepository.getById(id);
    }

    public Collection<Person> getAll() {
        return personRepository.getAll();
    }

    public void addPerson(PersonDto person) {
        personRepository.addPerson(person);
    }

    public void removePerson(long id) {
        personRepository.removePerson(id);
    }

    public boolean updatePerson(PersonDto person) {
        return personRepository.updatePerson(person);
    }
}
