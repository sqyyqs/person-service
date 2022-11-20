package com.example.demo.controller;


import com.example.demo.domain.Person;
import com.example.demo.domain.PersonDto;
import com.example.demo.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class PersonController {
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/by-id")
    public ResponseEntity<Person> getById(@RequestParam long id) {
        logger.debug("Invoke getById({}).", id);
        Person result = personService.getById(id);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Person>> getAll() {
        return ResponseEntity.ok(personService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPerson(@RequestBody PersonDto person) {
        personService.addPerson(person);
        return ResponseEntity.ok("{}");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> removePerson(@RequestParam long id) {
        personService.removePerson(id);
        return ResponseEntity.ok("{}");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePerson(@RequestBody PersonDto person) {
        boolean updateStatus = personService.updatePerson(person);
        return ResponseEntity.ok("{\"status\":" + updateStatus + "}");
    }

}
