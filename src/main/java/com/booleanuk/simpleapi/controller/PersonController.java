package com.booleanuk.simpleapi.controller;

import com.booleanuk.simpleapi.model.Person;
import com.booleanuk.simpleapi.model.Planet;
import com.booleanuk.simpleapi.repository.PersonRepository;
import com.booleanuk.simpleapi.repository.PlanetRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("people")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PlanetRepository planetRepository;

    @PostMapping
    public ResponseEntity<Person> createPerson (@RequestBody Person person) {
        Planet planet = this.planetRepository.findById(person.getPlanet().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        person.setPlanet(planet);

        return new ResponseEntity<Person>(this.personRepository.save(person), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable int id) {
        return new ResponseEntity<>(this.personRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No person with that id were found")), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        Person updatePerson = this.personRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Could not find person with given id"));
        Planet planet = this.planetRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Could not find planet with given id"));
        person.setPlanet(planet);

        updatePerson.setName(person.getName());
        updatePerson.setAge(person.getAge());
        updatePerson.setDescription(person.getDescription());
        updatePerson.setAlive(person.isAlive());
        updatePerson.setPlanet(person.getPlanet());

        return new ResponseEntity<Person>(this.personRepository.save(updatePerson), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable int id) {
        System.out.println(id);
        Person deletePerson = this.personRepository.findById(id).orElseThrow();
        System.out.println(deletePerson);
        this.personRepository.delete(deletePerson);
        return ResponseEntity.ok(deletePerson);
    }
}
